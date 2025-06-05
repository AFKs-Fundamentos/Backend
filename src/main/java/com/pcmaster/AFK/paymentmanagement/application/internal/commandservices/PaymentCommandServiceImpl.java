package com.pcmaster.AFK.paymentmanagement.application.internal.commandservices;

import com.pcmaster.AFK.paymentmanagement.application.internal.outboundservices.acl.ExternalCartShoppingService;
import com.pcmaster.AFK.paymentmanagement.config.StripeConfig;
import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;
import com.pcmaster.AFK.paymentmanagement.domain.services.PaymentCommandService;
import com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories.CurrencyRepository;
import com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final StripeConfig stripeConfig;
    private final ExternalCartShoppingService externalCartShoppingService;
    private final CurrencyRepository currencyRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, StripeConfig stripeConfig, ExternalCartShoppingService externalCartShoppingService, CurrencyRepository currencyRepository) {
        this.paymentRepository = paymentRepository;
        this.stripeConfig = stripeConfig;
        this.externalCartShoppingService = externalCartShoppingService;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public PaymentIntent handle(CreatePaymentCommand command) {
        Stripe.apiKey = stripeConfig.getApiKey();
        Map<String, Object> paymentIntentParams = new HashMap<>();

        Double totalPrice = externalCartShoppingService.fetchTotalPriceByCartShoppingId(command.cartShoppingId());
        if (totalPrice == null) {
            throw new IllegalArgumentException("Total price not found for cartShoppingId: " + command.cartShoppingId());
        }
        var productNameOptional = externalCartShoppingService.fetchProductNameByCartShoppingId(command.cartShoppingId());
        if (productNameOptional.isEmpty()) {
            throw new IllegalArgumentException("Product name not found for cartShoppingId: " + command.cartShoppingId());
        }

        var currency = this.currencyRepository.findByName(Currencies.valueOf(command.currency()))
                .orElseThrow(() -> new IllegalArgumentException("Currency with name " + command.currency() + " not found"));

        paymentIntentParams.put("amount", totalPrice);
        paymentIntentParams.put("currency", command.currency());
        paymentIntentParams.put("description", productNameOptional.get());
        paymentIntentParams.put("receipt_email", command.receiptEmail());
        paymentIntentParams.put("payment_method_types", new String[]{"card"});
        try{
            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
            // Convert Stripe timestamp to Date
            long stripeTimestamp = paymentIntent.getCreated(); // Stripe timestamp in UNIX format
            Date createdDate = Date.from(Instant.ofEpochSecond(stripeTimestamp));
            // Save payment details in the database
            Payment payment = new Payment(command);
            payment.setAmount(totalPrice.intValue());
            payment.setCurrency(currency);
            payment.setStripePaymentId(paymentIntent.getId());
            payment.setStatus(paymentIntent.getStatus());
            payment.setDescription(productNameOptional.get());
            payment.setReceipt_email(command.receiptEmail());
            payment.setCreated(createdDate);
            paymentRepository.save(payment);

            return paymentIntent;
        } catch (StripeException e) {
            throw new IllegalArgumentException("Error creating payment intent: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException {
        Stripe.apiKey = stripeConfig.getApiKey();
        PaymentIntent confirmedPaymentIntent = PaymentIntent.retrieve(paymentIntentId);
        if (confirmedPaymentIntent == null) {
            throw new IllegalArgumentException("Payment intent with id %s not found".formatted(paymentIntentId));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa"); // Supported payment method, can be dynamic if required
        confirmedPaymentIntent.confirm(params);
        // Update the payment in the database
        Optional<Payment> paymentOptional = paymentRepository.findByStripePaymentId(paymentIntentId);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus(confirmedPaymentIntent.getStatus());
            payment.setDescription(confirmedPaymentIntent.getDescription());
            payment.setReceipt_email(confirmedPaymentIntent.getReceiptEmail());
            paymentRepository.save(payment);
        } else {
            throw new IllegalArgumentException("Payment with Stripe ID %s not found in the database".formatted(paymentIntentId));
        }
        return confirmedPaymentIntent;
    }

}

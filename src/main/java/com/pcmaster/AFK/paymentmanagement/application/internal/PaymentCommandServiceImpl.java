package com.pcmaster.AFK.paymentmanagement.application.internal;

import com.pcmaster.AFK.paymentmanagement.config.StripeConfig;
import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.paymentmanagement.domain.services.PaymentCommandService;
import com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final StripeConfig stripeConfig;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, StripeConfig stripeConfig) {
        this.paymentRepository = paymentRepository;
        this.stripeConfig = stripeConfig;
    }

    @Override
    public PaymentIntent handle(CreatePaymentCommand command) {
        Stripe.apiKey = stripeConfig.getApiKey();
        Map<String, Object> paymentIntentParams = new HashMap<>();
        paymentIntentParams.put("amount", command.amount());
        paymentIntentParams.put("currency", command.currency());
        paymentIntentParams.put("payment_method_types", new String[]{"card"}); // Agr
        try{
            return PaymentIntent.create(paymentIntentParams);
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
        return confirmedPaymentIntent;
    }

}

package com.pcmaster.AFK.paymentmanagement.domain.services;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.CreateAdvisoryCommand;
import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.Optional;

public interface PaymentCommandService {
    PaymentIntent handle(CreatePaymentCommand command);
    PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException;
}

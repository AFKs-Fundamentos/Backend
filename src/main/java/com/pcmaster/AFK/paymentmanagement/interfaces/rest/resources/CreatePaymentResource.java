package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;

import java.time.LocalTime;

public record CreatePaymentResource(
        int amount,
        Currency currency,
        String status,
        String description,
        String receiptEmail
) {
}

package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;

import java.util.Date;

public record PaymentResource(Long paymentId,
        int amount,
        String currency,
        String status,
        String description,
        String receiptEmail,
        Date created) {
}

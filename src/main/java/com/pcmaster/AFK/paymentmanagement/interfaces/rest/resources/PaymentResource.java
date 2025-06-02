package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;

import java.time.LocalTime;
import java.util.Date;

public record PaymentResource(Long paymentId,
        int amount,
        Currency currency,
        String status,
        String description,
        String receiptEmail,
        Date created) {
}

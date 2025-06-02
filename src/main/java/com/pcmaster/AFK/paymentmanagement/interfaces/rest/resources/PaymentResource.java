package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;

public record PaymentResource(Long paymentId,
        int amount,
        Currency currency,
        String status) {

}

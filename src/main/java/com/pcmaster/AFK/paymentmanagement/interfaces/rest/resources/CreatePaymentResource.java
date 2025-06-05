package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;

public record CreatePaymentResource(
        Long cartShoppingId,
        int amount,
        String currency,
        String status,
        String description,
        String receiptEmail
) {
}

package com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;

public record CreatePaymentResource(
        Long cartShoppingId,
        int amount,
        Currencies currencies,
        String status,
        String description,
        String receiptEmail
) {
}

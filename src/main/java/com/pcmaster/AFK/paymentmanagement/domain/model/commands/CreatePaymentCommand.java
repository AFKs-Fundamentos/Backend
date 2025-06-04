package com.pcmaster.AFK.paymentmanagement.domain.model.commands;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;

public record CreatePaymentCommand(Long cartShoppingId,
                                   int amount,
                                   String currency,
                                   String status,
                                   String description,
                                   String receiptEmail) {
}

package com.pcmaster.AFK.paymentmanagement.domain.model.commands;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;

public record CreatePaymentCommand(int amount,
                                   Currency currency,
                                   String status) {
}

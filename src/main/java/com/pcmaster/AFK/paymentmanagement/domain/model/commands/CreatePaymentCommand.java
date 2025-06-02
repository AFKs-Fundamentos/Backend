package com.pcmaster.AFK.paymentmanagement.domain.model.commands;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;

import java.time.LocalTime;

public record CreatePaymentCommand(int amount,
                                   Currency currency,
                                   String status,
                                   String description,
                                   String receiptEmail) {
}

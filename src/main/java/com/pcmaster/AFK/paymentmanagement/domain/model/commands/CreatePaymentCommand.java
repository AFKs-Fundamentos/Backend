package com.pcmaster.AFK.paymentmanagement.domain.model.commands;

public record CreatePaymentCommand(Long amount,
                                   String currency,
                                   String stripePaymentId,
                                   String status) {
}

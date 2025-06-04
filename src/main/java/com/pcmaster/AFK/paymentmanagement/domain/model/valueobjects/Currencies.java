package com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects;

public enum Currencies {
    USD(1),
    PEN(2);

    private final int value;

    Currencies(int value) {
        this.value = value;
    }
}

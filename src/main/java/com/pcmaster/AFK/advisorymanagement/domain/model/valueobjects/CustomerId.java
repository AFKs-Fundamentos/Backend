package com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects;

import lombok.NonNull;

public record CustomerId(@NonNull Long customerId) {

    public CustomerId() {
        this(0L);
    }

    public CustomerId {
        if (customerId < 0) {
            throw new IllegalArgumentException("CustomerID cannot be negative");
        }
    }

}

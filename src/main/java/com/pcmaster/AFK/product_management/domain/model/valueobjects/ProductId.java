package com.pcmaster.AFK.product_management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(Long productId) {
    public ProductId {
        if (productId < 0) {
            throw new IllegalArgumentException("Product productId cannot be negative");
        }
    }

    public ProductId() {
        this(0L);
    }

    public Long getValue() {
        return productId;
    }
}

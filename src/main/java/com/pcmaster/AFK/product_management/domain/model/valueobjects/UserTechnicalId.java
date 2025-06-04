package com.pcmaster.AFK.product_management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserTechnicalId(Long userTechnicalId) {
    public UserTechnicalId {
        if (userTechnicalId < 0) {
            throw new IllegalArgumentException("UserTechnical userTechnicalId cannot be negative");
        }
    }
    public UserTechnicalId() {
        this(0L);
    }
    public Long getValue() {
        return userTechnicalId;
    }
}

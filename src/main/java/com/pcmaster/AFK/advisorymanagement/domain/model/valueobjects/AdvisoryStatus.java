package com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
public enum AdvisoryStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
}

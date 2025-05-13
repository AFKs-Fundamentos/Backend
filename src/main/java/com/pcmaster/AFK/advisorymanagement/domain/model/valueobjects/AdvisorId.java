package com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record AdvisorId(@NotNull long advisorId){
    public AdvisorId() {
            this(0L);
    }

    public AdvisorId {
        if (advisorId < 0) {
            throw new IllegalArgumentException("AdvisorID cannot be negative");
        }
    }
}

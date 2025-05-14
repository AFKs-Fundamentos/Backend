package com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum AdvisoryType {
    VIRTUAL,
    IN_PERSON,
}


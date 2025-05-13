package com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources;

import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryType;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAdvisoryResource(
        AdvisoryType advisoryType,
        AdvisoryStatus advisoryStatus,
        Long advisorId,
        Long customerId,
        LocalDate advisoryDate,
        LocalTime advisoryTime,
        String meetUrl,
        String clientEmail,
        String advisoryDescription,
        String location
) {
}

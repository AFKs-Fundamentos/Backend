package com.pcmaster.AFK.advisorymanagement.domain.model.commands;

import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryType;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateAdvisoryCommand(
        Long id,
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
) {}
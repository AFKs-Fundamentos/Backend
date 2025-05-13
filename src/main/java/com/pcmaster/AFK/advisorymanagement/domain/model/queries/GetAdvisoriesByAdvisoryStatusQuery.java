package com.pcmaster.AFK.advisorymanagement.domain.model.queries;

import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;

public record GetAdvisoriesByAdvisoryStatusQuery(AdvisoryStatus advisoryStatus) {
}

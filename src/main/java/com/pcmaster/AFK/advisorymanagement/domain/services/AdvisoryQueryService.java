package com.pcmaster.AFK.advisorymanagement.domain.services;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AdvisoryQueryService {

    List<Advisory> handle(GetAdvisoriesByAdvisoryStatusQuery query);
    List<Advisory> handle(GetAdvisoriesByAdvisoryTypeQuery query);
    List<Advisory> handle(GetAdvisoriesByCustomerIdQuery query);
    List<Advisory> handle(GetAdvisoriesByAdvisorIdQuery query);


    Optional<Advisory> handle(GetAdvisoryByIdQuery query);
}

package com.pcmaster.AFK.advisorymanagement.application.internal.queryservices;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.*;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryQueryService;
import com.pcmaster.AFK.advisorymanagement.infrastructure.persistence.jpa.repositories.AdvisoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisoryQueryServiceImpl implements AdvisoryQueryService {

    private final AdvisoryRepository advisoryRepository;

    public AdvisoryQueryServiceImpl(AdvisoryRepository advisoryRepository) {
        this.advisoryRepository = advisoryRepository;
    }


    @Override
    public List<Advisory> handle(GetAdvisoriesByAdvisoryStatusQuery query) {
        return advisoryRepository.findByAdvisoryStatus(query.advisoryStatus());
    }

    @Override
    public List<Advisory> handle(GetAdvisoriesByAdvisoryTypeQuery query) {
        return advisoryRepository.findByAdvisoryType(query.advisoryType());
    }

    @Override
    public List<Advisory> handle(GetAdvisoriesByCustomerIdQuery query) {
        return advisoryRepository.findByCustomerId(query.customerId());
    }

    @Override
    public List<Advisory> handle(GetAdvisoriesByAdvisorIdQuery query) {
        return advisoryRepository.findByAdvisorId(query.advisorId());
    }

    @Override
    public Optional<Advisory> handle(GetAdvisoryByIdQuery query) {
        return advisoryRepository.findById(query.id());
    }


}

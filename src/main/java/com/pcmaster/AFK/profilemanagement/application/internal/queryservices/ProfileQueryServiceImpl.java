package com.pcmaster.AFK.profilemanagement.application.internal.queryservices;

import com.pcmaster.AFK.profilemanagement.domain.model.aggregates.Profile;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetAllProfilesQuery;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetProfileByIdQuery;
import com.pcmaster.AFK.profilemanagement.domain.services.ProfileQueryService;
import com.pcmaster.AFK.profilemanagement.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return this.profileRepository.findById(query.id());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return this.profileRepository.findAll();
    }
}

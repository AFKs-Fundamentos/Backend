package com.pcmaster.AFK.profilemanagement.domain.services;

import com.pcmaster.AFK.profilemanagement.domain.model.aggregates.Profile;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetAllProfilesQuery;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}

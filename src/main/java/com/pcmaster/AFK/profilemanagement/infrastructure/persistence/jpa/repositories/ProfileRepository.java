package com.pcmaster.AFK.profilemanagement.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

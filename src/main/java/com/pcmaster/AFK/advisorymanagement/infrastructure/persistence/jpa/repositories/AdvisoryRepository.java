package com.pcmaster.AFK.advisorymanagement.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisorId;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryType;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.CustomerId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdvisoryRepository extends JpaRepository<Advisory, Long> {

    boolean existsById(Long advisoryId);
    boolean existsByAdvisoryTimeAndAdvisoryDate(LocalTime advisoryTime, LocalDate advisoryDate);


    List<Advisory> findByAdvisoryType(AdvisoryType advisoryType);
    List<Advisory> findByCustomerId(CustomerId customerId);

    List<Advisory> findByAdvisorId(AdvisorId advisorId);

    List<Advisory> findByAdvisoryStatus(AdvisoryStatus advisoryStatus);

    Optional<Advisory> findById(Long advisoryId);

    @Transactional
    void delete(Advisory advisoryId);

}

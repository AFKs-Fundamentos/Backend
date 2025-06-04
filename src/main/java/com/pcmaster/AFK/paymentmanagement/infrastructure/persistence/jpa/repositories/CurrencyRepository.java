package com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.paymentmanagement.domain.model.entities.Currency;
import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(Currencies name);
    Optional<Currency> findByName(Currencies name);
}

package com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

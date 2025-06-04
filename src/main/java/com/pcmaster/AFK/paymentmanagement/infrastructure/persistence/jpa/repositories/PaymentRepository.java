package com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByStripePaymentId(String stripePaymentId);
}

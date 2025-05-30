package com.pcmaster.AFK.paymentmanagement.domain.model.aggregates;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends AuditableAbstractAggregateRoot<Payment> {
    private Long id;

    private Long amount;

    private String currency;

    private String stripePaymentId;

    private String status;

    public Payment() {
        // Default constructor for JPA
    }

    public Payment(CreatePaymentCommand command) {
        this.amount = command.amount();
        this.currency = command.currency();
        this.stripePaymentId = command.stripePaymentId();
        this.status = command.status();
    }
}

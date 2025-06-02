package com.pcmaster.AFK.paymentmanagement.domain.model.aggregates;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currency;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "payments")
public class Payment extends AuditableAbstractAggregateRoot<Payment> {
    private int amount;

    private Currency currency;

    private String status;

    public Payment(int amount,
                   Currency currency,
                   String status) {
        this.amount = amount;
        this.currency = Currency.USD;
        this.status = status;
    }

    public Payment(CreatePaymentCommand command) {
        this.amount = command.amount();
        this.currency = command.currency();
        this.status = command.status();
    }
}

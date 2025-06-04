package com.pcmaster.AFK.paymentmanagement.domain.model.aggregates;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.paymentmanagement.domain.model.entities.Currency;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "payments")
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    private int amount;

    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", nullable = false)
    private String status;

    private String description;

    private String receipt_email;

    private String stripePaymentId;

    @Column(name = "created", columnDefinition = "TIMESTAMP(6)")
    private Date created;

    public Payment(int amount,
                   Currency currency,
                   String status,
                   String description,
                   String receipt_email,
                   Date created) {
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.description = description;
        this.receipt_email = receipt_email;
        this.created = created;
    }

    public Payment(CreatePaymentCommand command) {
        this.amount = command.amount();
        this.currency = Currency.toCurrencyFromName(command.currency());
        this.status = command.status();
        this.description = command.description();
        this.receipt_email = command.receiptEmail();
    }
}

package com.pcmaster.AFK.advisorymanagement.domain.model.aggregates;


import com.pcmaster.AFK.advisorymanagement.domain.model.commands.CreateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.UpdateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisorId;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryType;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.CustomerId;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "advisories")
@Setter
@Getter
public class Advisory extends AuditableAbstractAggregateRoot<Advisory> {

    @Column(name = "advisory_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvisoryType advisoryType;

    @Column(name = "advisory_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvisoryStatus advisoryStatus;

    @Column (name = "advisor_id", nullable = false)
    @Embedded
    private AdvisorId advisorId;

    @Column (name = "client_id", nullable = false)
    @Embedded
    private CustomerId customerId;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalDate advisoryDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime advisoryTime;

    @NotNull
    @Email(message = "Debe ser un email válido")
    private String clientEmail;

    private String advisoryDescription;

    private String location;

    @Column(name = "meet_url")
    private String meetUrl;

    public Advisory(
            AdvisoryType advisoryType,
            AdvisoryStatus advisoryStatus,
            AdvisorId advisorId,
            CustomerId customerId,
            LocalDate advisoryDate,
            LocalTime advisoryTime,
            String meetUrl,
            String clientEmail,
            String advisoryDescription,
            String location
    ) {
        this.advisoryType = AdvisoryType.VIRTUAL;
        this.advisoryStatus = AdvisoryStatus.PENDING;
        this.advisorId = new AdvisorId(advisorId.advisorId());
        this.customerId = new CustomerId(customerId.customerId());
        this.advisoryDate = advisoryDate;
        this.advisoryTime = advisoryTime;
        this.meetUrl = meetUrl;
        this.clientEmail = clientEmail;
        this.advisoryDescription = advisoryDescription;
        this.location = location;
    }

    public Advisory (CreateAdvisoryCommand command) {
        this.advisoryType = command.advisoryType();
        this.advisoryStatus = command.advisoryStatus();
        this.advisorId = new AdvisorId(command.advisorId().advisorId());
        this.customerId = new CustomerId(command.customerId().customerId());
        this.advisoryDate = command.advisoryDate();
        this.advisoryTime = command.advisoryTime();
        this.meetUrl = command.meetUrl();
        this.clientEmail = command.clientEmail();
        this.advisoryDescription = command.advisoryDescription();
        this.location = command.location();
    }

    public Advisory udpdateInformation(
            AdvisoryType advisoryType,
            AdvisoryStatus advisoryStatus,
            AdvisorId advisorId,
            CustomerId customerId,
            LocalDate advisoryDate,
            LocalTime advisoryTime,
            String meetUrl,
            String clientEmail,
            String advisoryDescription,
            String location
    ) {
        this.advisoryType = AdvisoryType.VIRTUAL;
        this.advisoryStatus = AdvisoryStatus.PENDING;
        this.advisorId = new AdvisorId(advisorId.advisorId());
        this.customerId = new CustomerId(customerId.customerId());
        this.advisoryDate = advisoryDate;
        this.advisoryTime = advisoryTime;
        this.meetUrl = meetUrl;
        this.clientEmail = clientEmail;
        this.advisoryDescription = advisoryDescription;
        this.location = location;
        return this;
    }

    public Advisory(){}


}

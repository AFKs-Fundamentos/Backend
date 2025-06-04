package com.pcmaster.AFK.paymentmanagement.domain.model.entities;

import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "enrollment_statuses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currencies name;

    public Currency(Currencies name) {
        this.name = name;
    }

    public String getCurrencyName() {
        return name.name();
    }

    public static Currency getDefaultCurrency() {
        return new Currency(Currencies.USD);
    }

    public static Currency toCurrencyFromName(String name){
        return new Currency(Currencies.valueOf(name));
    }
}

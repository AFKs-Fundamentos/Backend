package com.pcmaster.AFK.paymentmanagement.application.internal.commandservices;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.SeedCurrenciesCommand;
import com.pcmaster.AFK.paymentmanagement.domain.model.entities.Currency;
import com.pcmaster.AFK.paymentmanagement.domain.model.valueobjects.Currencies;
import com.pcmaster.AFK.paymentmanagement.domain.services.CurrencyCommandService;
import com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CurrencyCommandServiceImpl implements CurrencyCommandService {

    private final CurrencyRepository currencyRepository;

    public CurrencyCommandServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void handle(SeedCurrenciesCommand command) {
        Arrays.stream(Currencies.values())
                .forEach(currency -> {
                    if(!currencyRepository.existsByName(currency)) {
                        currencyRepository.save(new Currency(Currencies.valueOf(currency.name())));
                    }
                } );
    }
}

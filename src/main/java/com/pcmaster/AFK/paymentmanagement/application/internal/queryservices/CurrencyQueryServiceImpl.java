package com.pcmaster.AFK.paymentmanagement.application.internal.queryservices;

import com.pcmaster.AFK.paymentmanagement.domain.model.entities.Currency;
import com.pcmaster.AFK.paymentmanagement.domain.model.queries.GetCurrencyByNameQuery;
import com.pcmaster.AFK.paymentmanagement.domain.services.CurrencyQueryService;
import com.pcmaster.AFK.paymentmanagement.infrastructure.persistence.jpa.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyQueryServiceImpl implements CurrencyQueryService {

    private final CurrencyRepository currencyRepository;

    public CurrencyQueryServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<Currency> handle(GetCurrencyByNameQuery query) {
        return this.currencyRepository.findByName(query.name());
    }
}

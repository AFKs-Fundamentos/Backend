package com.pcmaster.AFK.paymentmanagement.domain.services;

import com.pcmaster.AFK.paymentmanagement.domain.model.entities.Currency;
import com.pcmaster.AFK.paymentmanagement.domain.model.queries.GetCurrencyByNameQuery;

import java.util.List;
import java.util.Optional;

public interface CurrencyQueryService {
    Optional<Currency> handle(GetCurrencyByNameQuery query);
}

package com.pcmaster.AFK.paymentmanagement.domain.services;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.SeedCurrenciesCommand;

public interface CurrencyCommandService {
    void handle(SeedCurrenciesCommand command);
}

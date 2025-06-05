package com.pcmaster.AFK.paymentmanagement.application.internal.eventhandlers;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.SeedCurrenciesCommand;
import com.pcmaster.AFK.paymentmanagement.domain.services.CurrencyCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CurrencyReadyEventHandler {
    private final CurrencyCommandService currencyCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyReadyEventHandler.class);

    public CurrencyReadyEventHandler(CurrencyCommandService currencyCommandService) {
        this.currencyCommandService = currencyCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if currencies seeding is needed for {} at {}", applicationName, currentTimestamp());

        currencyCommandService.handle(new SeedCurrenciesCommand());
        LOGGER.info("Currency seeding verification finished for {} at {}",
                applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

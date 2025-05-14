package com.pcmaster.AFK.advisorymanagement.domain.services;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.CreateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.DeleteAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.UpdateAdvisoryCommand;

import java.util.Optional;

public interface AdvisoryCommandService {

    Optional<Advisory> handle(CreateAdvisoryCommand command);
    Void handle(DeleteAdvisoryCommand command);
    Optional<Advisory> handle(UpdateAdvisoryCommand command);



}

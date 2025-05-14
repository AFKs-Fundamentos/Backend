package com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform;

import com.pcmaster.AFK.advisorymanagement.domain.model.commands.CreateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.CreateAdvisoryResource;


public class CreateAdvisoryCommandFromResourceAssembler {
    public static CreateAdvisoryCommand toCommandFromResource(CreateAdvisoryResource resource) {
        return new CreateAdvisoryCommand(
                resource.advisoryType(),
                resource.advisoryStatus(),
                resource.advisorId(),
                resource.customerId(),
                resource.advisoryDate(),
                resource.advisoryTime(),
                resource.meetUrl(),
                resource.clientEmail(),
                resource.advisoryDescription(),
                resource.location()
        );
    }
}

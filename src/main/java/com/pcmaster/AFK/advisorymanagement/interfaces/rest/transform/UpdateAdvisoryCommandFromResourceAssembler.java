package com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform;

import com.pcmaster.AFK.advisorymanagement.domain.model.commands.UpdateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.UpdateAdvisoryResource;

public class UpdateAdvisoryCommandFromResourceAssembler {
    public static UpdateAdvisoryCommand toCommandFromResource(Long advisoryId, UpdateAdvisoryResource resource) {
        return new UpdateAdvisoryCommand(
                advisoryId,
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

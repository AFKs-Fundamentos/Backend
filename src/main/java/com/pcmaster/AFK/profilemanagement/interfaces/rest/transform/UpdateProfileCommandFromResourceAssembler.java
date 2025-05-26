package com.pcmaster.AFK.profilemanagement.interfaces.rest.transform;

import com.pcmaster.AFK.profilemanagement.domain.model.commands.UpdateProfileCommand;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long id,UpdateProfileResource resource) {
        return new UpdateProfileCommand(id, resource.firstName(), resource.lastName(), resource.email(), resource.phone());
    }
}

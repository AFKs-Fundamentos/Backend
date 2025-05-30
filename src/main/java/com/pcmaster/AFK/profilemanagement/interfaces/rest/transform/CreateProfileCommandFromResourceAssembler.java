package com.pcmaster.AFK.profilemanagement.interfaces.rest.transform;

import com.pcmaster.AFK.profilemanagement.domain.model.commands.CreateProfileCommand;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(),resource.lastName(),resource.email(),resource.phone());
    }
}

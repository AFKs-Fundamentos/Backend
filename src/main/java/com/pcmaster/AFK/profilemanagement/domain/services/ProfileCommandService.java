package com.pcmaster.AFK.profilemanagement.domain.services;


import com.pcmaster.AFK.profilemanagement.domain.model.aggregates.Profile;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.CreateProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.DeleteProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Void handle(DeleteProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
}

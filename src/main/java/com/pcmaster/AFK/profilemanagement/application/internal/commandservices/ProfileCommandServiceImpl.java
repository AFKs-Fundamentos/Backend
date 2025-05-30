package com.pcmaster.AFK.profilemanagement.application.internal.commandservices;

import com.pcmaster.AFK.profilemanagement.domain.model.aggregates.Profile;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.CreateProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.DeleteProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.UpdateProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.services.ProfileCommandService;
import com.pcmaster.AFK.profilemanagement.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService{

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var profile = new Profile(command);
        try{
            this.profileRepository.save(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving Profile: " + e.getMessage());
        }
        return profile.getId();
    }

    @Override
    public void handle(DeleteProfileCommand command) {
        profileRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Profile with id: " + command.id() + " does not exist"));
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var profileId = command.id();
        if(!this.profileRepository.existsById(profileId)) {
            throw new IllegalArgumentException("Profile with id " + profileId + " does not exist");
        }
        var profileToUpdate = this.profileRepository.findById(profileId).get();
        profileToUpdate.updateProfile(command);
        try {
            var updatedProfile = this.profileRepository.save(profileToUpdate);
            return Optional.of(updatedProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Profile: " + e.getMessage());
        }
    }
}

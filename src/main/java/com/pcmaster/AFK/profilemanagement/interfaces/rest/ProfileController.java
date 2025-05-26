package com.pcmaster.AFK.profilemanagement.interfaces.rest;


import com.pcmaster.AFK.profilemanagement.domain.model.commands.DeleteProfileCommand;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetAllProfilesQuery;
import com.pcmaster.AFK.profilemanagement.domain.model.queries.GetProfileByIdQuery;
import com.pcmaster.AFK.profilemanagement.domain.services.ProfileCommandService;
import com.pcmaster.AFK.profilemanagement.domain.services.ProfileQueryService;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.resources.CreateProfileResource;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.resources.ProfileResource;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.resources.UpdateProfileResource;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.pcmaster.AFK.profilemanagement.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile API")
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new profile", description = "Creates a new profile with the provided details.")
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource createProfileResource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(createProfileResource);
        var profileId = this.profileCommandService.handle(createProfileCommand);
        if(profileId.equals(0L)) return ResponseEntity.badRequest().build();
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var optionalProfile = this.profileQueryService.handle(getProfileByIdQuery);
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{profileId}")
    @Operation(summary = "Get profile by ID", description = "Retrieves a profile by its unique ID.")
    public ResponseEntity<List<ProfileResource>> getProfileByUserId(@Parameter(description = "ProfileId") @PathVariable Long id){
        var getProfilesByUserIdQuery = new GetProfileByIdQuery(id);
        var profiles = profileQueryService.handle(getProfilesByUserIdQuery);
        var profilesResource = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profilesResource);
    }

    @GetMapping
    @Operation(summary = "Get all profiles", description = "Retrieves a list of all profiles.")
    public ResponseEntity<List<ProfileResource>> getAllProfiles(){
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResource = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile", description = "Updates an existing profile with the provided details.")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileResource resource){
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalProfile = this.profileCommandService.handle(updateProfileCommand);
        if(optionalProfile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id){
        var deleteProfileCommand = new DeleteProfileCommand(id);
        this.profileCommandService.handle(deleteProfileCommand);
        return ResponseEntity.noContent().build();
    }
}

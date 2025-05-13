package com.pcmaster.AFK.advisorymanagement.interfaces.rest;

import com.pcmaster.AFK.advisorymanagement.domain.model.queries.GetAdvisoryByIdQuery;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryCommandService;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryQueryService;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.AdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.CreateAdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform.AdvisoryResourceFromEntityToAssembler;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform.CreateAdvisoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/advisories", produces = APPLICATION_JSON_VALUE)
@Tag(name="Advisory", description = "Advisory Management endpoints")
public class AdvisoryController {
    private final AdvisoryCommandService advisoryCommandService;
    private final AdvisoryQueryService advisoryQueryService;

    public AdvisoryController(AdvisoryCommandService advisoryCommandService, AdvisoryQueryService advisoryQueryService) {
        this.advisoryCommandService = advisoryCommandService;
        this.advisoryQueryService = advisoryQueryService;
    }

    @PostMapping
    @Valid
    @Operation(summary = "Create Advisory", description = "Creates a new advisory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Advisory created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Advisory not found")})
    public ResponseEntity<AdvisoryResource> createAdvisory(@RequestBody CreateAdvisoryResource resource) {
        var createAdvisoryCommand = CreateAdvisoryCommandFromResourceAssembler.toCommandFromResource(resource);

        var advisoryId = this.advisoryCommandService.handle(createAdvisoryCommand);

        if(advisoryId.isEmpty()) { return ResponseEntity.notFound().build(); }

        var getAdvisoryByIdQuery = new GetAdvisoryByIdQuery(advisoryId.get().id);

        var optionalAdvisory = this.advisoryQueryService.handle(getAdvisoryByIdQuery);

        var advisoryResource = AdvisoryResourceFromEntityToAssembler.toResourceFromEntity(optionalAdvisory.get());

        return ResponseEntity.ok(advisoryResource);
    }








}

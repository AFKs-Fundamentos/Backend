package com.pcmaster.AFK.advisorymanagement.interfaces.rest;

import com.pcmaster.AFK.advisorymanagement.domain.model.commands.DeleteAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.GetAdvisoriesByAdvisorIdQuery;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.GetAdvisoriesByAdvisoryStatusQuery;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.GetAdvisoriesByCustomerIdQuery;
import com.pcmaster.AFK.advisorymanagement.domain.model.queries.GetAdvisoryByIdQuery;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisorId;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.AdvisoryStatus;
import com.pcmaster.AFK.advisorymanagement.domain.model.valueobjects.CustomerId;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryCommandService;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryQueryService;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.AdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.CreateAdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.UpdateAdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform.AdvisoryResourceFromEntityAssembler;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform.CreateAdvisoryCommandFromResourceAssembler;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform.UpdateAdvisoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        var advisoryResource = AdvisoryResourceFromEntityAssembler.toResourceFromEntity(optionalAdvisory.get());
        return ResponseEntity.ok(advisoryResource);
    }

    @PutMapping({"/{advisoryId}"})
    @Operation(summary = "Update a advisory")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisory updated"),
            @ApiResponse(responseCode = "404", description = "Advisory not found")})
    public ResponseEntity<AdvisoryResource> updateAdvisory(@PathVariable Long advisoryId, @RequestBody UpdateAdvisoryResource resource) {
        var updateAdvisoryCommand = UpdateAdvisoryCommandFromResourceAssembler
                .toCommandFromResource(advisoryId, resource);
        var advisoryOptional = this.advisoryCommandService.handle(updateAdvisoryCommand);
        if(advisoryOptional.isEmpty()) { return ResponseEntity.notFound().build(); }
        var updateEntity = advisoryOptional.get();
        var advisoryResource = AdvisoryResourceFromEntityAssembler.toResourceFromEntity(updateEntity);
        return ResponseEntity.ok(advisoryResource);
    }


    @DeleteMapping({"/{advisoryId}"})
    @Operation(summary = "Delete an advisory")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisory deleted"),
            @ApiResponse(responseCode = "404", description = "Advisory not found")})
    public ResponseEntity<?> deleteAdvisory(@PathVariable Long advisoryId) {
        var deleteCommand = new DeleteAdvisoryCommand(advisoryId);
        this.advisoryCommandService.handle(deleteCommand);
        return ResponseEntity.ok("Advisory with given id successfully deleted");
    }


    @GetMapping("/{advisoryId}")
    @Operation(summary = "Get an advisory by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisory found"),
            @ApiResponse(responseCode = "404", description = "Advisory not found")})
    public ResponseEntity<AdvisoryResource> getAdvisoryById(@PathVariable Long advisoryId) {
        var getAdvisoryByIdQuery = new GetAdvisoryByIdQuery(advisoryId);
        var optionalAdvisory = this.advisoryQueryService.handle(getAdvisoryByIdQuery);
        if(optionalAdvisory.isEmpty()) { return ResponseEntity.notFound().build(); }
        var advisoryResource = AdvisoryResourceFromEntityAssembler.toResourceFromEntity(optionalAdvisory.get());
        return ResponseEntity.ok(advisoryResource);
    }

    @GetMapping({"/advisory/customer/{customerId}"})
    @Operation(summary = "Get all advisories by customer id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisories found"),
            @ApiResponse(responseCode = "404", description = "Advisories not found")})
    public ResponseEntity<List<AdvisoryResource>> getAdvisoriesByCustomerId(@PathVariable Long customerId) {
        var getAdvisoriesByCustomerIdQuery = new GetAdvisoriesByCustomerIdQuery(customerId);
        var advisoryList = this.advisoryQueryService.handle(getAdvisoriesByCustomerIdQuery);
        if(advisoryList.isEmpty()) { return ResponseEntity.notFound().build(); }
        var advisoryResourceList = advisoryList.stream()
                .map(AdvisoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(advisoryResourceList);
    }

    @GetMapping({"/advisory/advisor/{advisorId}"})
    @Operation(summary = "Get all advisories by customer id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisories found"),
            @ApiResponse(responseCode = "404", description = "Advisories not found")})
    public ResponseEntity<List<AdvisoryResource>> getAdvisoriesByAdvisorId(@PathVariable Long advisorId) {
        var getAdvisoriesByAdvisorIdQuery = new GetAdvisoriesByAdvisorIdQuery(advisorId);
        var advisoryList = this.advisoryQueryService.handle(getAdvisoriesByAdvisorIdQuery);
        if(advisoryList.isEmpty()) { return ResponseEntity.notFound().build(); }
        var advisoryResourceList = advisoryList.stream()
                .map(AdvisoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(advisoryResourceList);
    }

    @GetMapping({"/advisory/status/{status}"})
    @Operation(summary = "Get all advisories by customer id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Advisories found"),
            @ApiResponse(responseCode = "404", description = "Advisories not found")})
    public ResponseEntity<List<AdvisoryResource>> getAdvisoriesByAdvisoryStatus(@PathVariable AdvisoryStatus status) {
        var getAdvisoriesByAdvisoryStatusQuery = new GetAdvisoriesByAdvisoryStatusQuery(status);
        var advisoryList = this.advisoryQueryService.handle(getAdvisoriesByAdvisoryStatusQuery);
        if(advisoryList.isEmpty()) { return ResponseEntity.notFound().build(); }
        var advisoryResourceList = advisoryList.stream()
                .map(AdvisoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(advisoryResourceList);

    }
}




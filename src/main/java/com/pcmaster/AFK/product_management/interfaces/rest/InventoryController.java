package com.pcmaster.AFK.product_management.interfaces.rest;

import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryByProductIdCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByIdQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByUserIdQuery;
import com.pcmaster.AFK.product_management.domain.services.InventoryCommandService;
import com.pcmaster.AFK.product_management.domain.services.InventoryQueryService;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.CreateInventoryResource;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.InventoryResource;
import com.pcmaster.AFK.product_management.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.pcmaster.AFK.product_management.interfaces.rest.transform.InventoryResourceFromEntityAssembler;


// ============================
import com.pcmaster.AFK.product_management.interfaces.rest.transform.UpdateInventoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//#########################################


@CrossOrigin(origins = "**", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/inventories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Inventories", description = "Inventory Management Endpoints")
public class InventoryController {

    private final InventoryQueryService inventoryQueryService;
    private final InventoryCommandService inventoryCommandService;

    public InventoryController(InventoryQueryService inventoryQueryService, InventoryCommandService inventoryCommandService) {
        this.inventoryQueryService = inventoryQueryService;
        this.inventoryCommandService = inventoryCommandService;
    }

    /**
     * Creates a new inventory.
     *
     * @param resource the resource containing inventory details
     * @return ResponseEntity with the created InventoryResource and HTTP status
     */
    @PostMapping
    public ResponseEntity<InventoryResource>  create(@RequestBody CreateInventoryResource resource){
        var createInventoryCommand = CreateInventoryCommandFromResourceAssembler.toCommandFromResource(resource);
        var inventoryId = this.inventoryCommandService.handle(createInventoryCommand);
        if(inventoryId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }
        var getInventoryByIdQuery = new GetInventoryByIdQuery(inventoryId);
        var optionalInventory = this.inventoryQueryService.handle(getInventoryByIdQuery);
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(optionalInventory.get());
        return new ResponseEntity<>(inventoryResource, HttpStatus.CREATED);
    }
    /**
     * Updates an existing inventory.
     *
     * @param resource the resource containing updated inventory details
     * @return ResponseEntity with the updated InventoryResource and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResource> update(@PathVariable Long id, @RequestBody InventoryResource resource){
        var updateInventoryCommand = UpdateInventoryCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalInventory = this.inventoryCommandService.handle(updateInventoryCommand);
        if(optionalInventory.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(optionalInventory.get());
        return ResponseEntity.ok(inventoryResource);
    }
    /**
     * Deletes an inventory by its ID.
     *
     * @param id the ID of the inventory to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        var deleteInventoryCommand = new DeleteInventoryCommand(id);
        this.inventoryCommandService.handle(deleteInventoryCommand);
        return ResponseEntity.noContent().build();
    }
    /**
     * Delete an inventory by product ID.
     * @param productId the ID of the product whose inventory is to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/productId/{productId}/")
    public ResponseEntity<?> deleteByProductId(@PathVariable Long productId){
        var deleteInventoryByProductIdCommand = new DeleteInventoryByProductIdCommand(productId);
        this.inventoryCommandService.handle(deleteInventoryByProductIdCommand);
        return ResponseEntity.noContent().build();
    }
    //#########################Query Methods#########################
    /**
     * Retrieves an inventory by its ID.
     *
     * @param id the ID of the inventory to be retrieved
     * @return ResponseEntity with the InventoryResource and HTTP status
     */
    @GetMapping("/{id}")
    public ResponseEntity<InventoryResource> getById(@PathVariable Long id){
        var getInventoryByIdQuery = new GetInventoryByIdQuery(id);
        var optionalInventory = this.inventoryQueryService.handle(getInventoryByIdQuery);
        if(optionalInventory.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(optionalInventory.get());
        return ResponseEntity.ok(inventoryResource);
    }
    /**
     * Retrieves an inventory by user technical ID.
     *
     * @param userTechnicalId the technical ID of the user whose inventory is to be retrieved
     * @return ResponseEntity with a list of InventoryResource and HTTP status
     */
    @GetMapping("/userTechnicalId/{userTechnicalId}")
    public ResponseEntity<List<InventoryResource>> getByUserTechnicalId(@PathVariable Long userTechnicalId) {
        var getInventoryByUserIdQuery = new GetInventoryByUserIdQuery(userTechnicalId);
        var inventories = this.inventoryQueryService.handle(getInventoryByUserIdQuery);
        if (inventories.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var inventoryResources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryResources);
    }
}

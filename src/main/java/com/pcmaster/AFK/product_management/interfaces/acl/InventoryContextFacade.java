package com.pcmaster.AFK.product_management.interfaces.acl;

import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryByProductIdCommand;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByProductIdQuery;
import com.pcmaster.AFK.product_management.domain.services.InventoryCommandService;
import com.pcmaster.AFK.product_management.domain.services.InventoryQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryContextFacade {

    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;

    public InventoryContextFacade(InventoryCommandService inventoryCommandService, InventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }


    /**
     * Deletes an inventory by product ID.
     *
     * @param productId the ID of the product whose inventory is to be deleted
     */

    public void deleteInventoryByProductId(Long productId) {
       var deleteInventoryByProductIdCommand = new DeleteInventoryByProductIdCommand(productId);
       inventoryCommandService.handle(deleteInventoryByProductIdCommand);

    }

    /**
     * Retrieves the inventory by product ID.
     *
     * @param productId the ID of the product whose inventory is to be retrieved
     * @return the inventory associated with the specified product ID
     */
    public boolean inventoryExistsByProductId(Long productId) {
        var getInventoryByProductIdQuery = new GetInventoryByProductIdQuery(productId);
        return inventoryQueryService.handle(getInventoryByProductIdQuery).isPresent();
    }
}

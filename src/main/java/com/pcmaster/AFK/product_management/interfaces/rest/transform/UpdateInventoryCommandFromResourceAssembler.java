package com.pcmaster.AFK.product_management.interfaces.rest.transform;

import com.pcmaster.AFK.product_management.domain.model.commands.UpdateInventoryCommand;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.InventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, InventoryResource resource) {
        return new UpdateInventoryCommand(
                inventoryId,
                resource.userTechnicalId(),
                resource.productId(),
                resource.stock(),
                resource.stockMin(),
                resource.stockMax()
        );
    }
}

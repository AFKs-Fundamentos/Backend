package com.pcmaster.AFK.product_management.interfaces.rest.transform;


import com.pcmaster.AFK.product_management.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {
    public static CreateInventoryCommand toCommandFromResource(CreateInventoryResource resource) {
        return new CreateInventoryCommand(
                resource.userTechnicalId(),
                resource.productId(),
                resource.stock(),
                resource.stockMin(),
                resource.stockMax()
        );
    }
}

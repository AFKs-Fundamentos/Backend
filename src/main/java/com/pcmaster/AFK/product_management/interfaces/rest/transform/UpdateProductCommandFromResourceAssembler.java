package com.pcmaster.AFK.product_management.interfaces.rest.transform;

import com.pcmaster.AFK.product_management.domain.model.commands.UpdateProductCommand;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.ProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long productId, ProductResource resource) {
        return new UpdateProductCommand(
                productId,
                resource.productName(),
                resource.photo(),
                resource.sku(),
                resource.category(),
                resource.price(),
                resource.description()
        );
    }
}

package com.pcmaster.AFK.product_management.interfaces.rest.transform;

import com.pcmaster.AFK.product_management.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.productName(),
                resource.photo(),
                resource.sku(),
                resource.category(),
                resource.price(),
                resource.description()
        );
    }
}

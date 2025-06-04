package com.pcmaster.AFK.product_management.interfaces.rest.resources;

public record CreateInventoryResource(
        Long userTechnicalId,
        Long productId,
        Long stock,
        Long stockMin,
        Long stockMax
) {
}

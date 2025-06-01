package com.pcmaster.AFK.product_management.domain.model.commands;

public record UpdateInventoryCommand(
        Long inventoryId,
        Long userTechnicalId,
        Long productId,
        Long stock,
        Long stockMin,
        Long stockMax

) {
}

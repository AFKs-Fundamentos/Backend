package com.pcmaster.AFK.product_management.domain.model.commands;

public record CreateInventoryCommand(
        Long userTechnicalId,
        Long productId,
        Long stock,
        Long stockMin,
        Long stockMax
) {
}
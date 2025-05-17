package com.pcmaster.AFK.product_managment.domain.model.commands;

public record UpdateProductCommand(
        Long productId,
        String productName,
        String photo,
        String sku,
        String category,
        Float price,
        Long stock,
        String description,
        Boolean isFavorite,
        Long characteristics_id
) {
}

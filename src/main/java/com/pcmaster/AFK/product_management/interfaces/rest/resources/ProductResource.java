package com.pcmaster.AFK.product_management.interfaces.rest.resources;

public record ProductResource(
        Long id,
        String productName,
        String photo,
        String sku,
        String category,
        Double price,
        String description
) {
}

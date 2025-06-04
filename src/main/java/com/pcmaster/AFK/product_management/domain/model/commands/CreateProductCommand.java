package com.pcmaster.AFK.product_management.domain.model.commands;

public record CreateProductCommand(
         String productName,
         String photo,
         String sku,
         String category,
         Double price,
         String description
) {

}

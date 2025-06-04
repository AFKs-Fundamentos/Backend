package com.pcmaster.AFK.product_management.interfaces.rest.resources;

public record CreateProductResource (
        String productName,
        String photo,
        String sku,
        String category,
        Double price,
        String description
){
}

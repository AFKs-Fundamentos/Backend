package com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources;

public record CartShoppingResource(
        Long id,
        Long userClientId,
        Long productId,
        String productName,
        Double productPrice,
        int quantity,
        String statusCartShoppingItem
) {
}

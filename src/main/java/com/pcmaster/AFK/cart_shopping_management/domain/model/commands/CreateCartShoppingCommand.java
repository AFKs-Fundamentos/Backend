package com.pcmaster.AFK.cart_shopping_management.domain.model.commands;

public record CreateCartShoppingCommand(
        Long userClientId,
        Long productId,
        String productName,
        Double productPrice,
        int quantity,
        String statusCartItem
) {
}

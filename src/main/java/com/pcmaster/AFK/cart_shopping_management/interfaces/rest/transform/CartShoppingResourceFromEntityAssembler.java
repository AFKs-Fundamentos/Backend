package com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources.CartShoppingResource;

public class CartShoppingResourceFromEntityAssembler {
    public static CartShoppingResource toResourceFromEntity(CartShopping entity){
        return new CartShoppingResource(
                entity.getId(),
                entity.getUserClientId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getQuantity(),
                entity.getStatusCartShoppingItem().toString()
        );

    }
}

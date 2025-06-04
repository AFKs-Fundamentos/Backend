package com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform;

import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.UpdateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources.CartShoppingResource;

public class UpdateCartShoppingCommandFromResourceAssembler {
    public static UpdateCartShoppingCommand toCommandFromResource(Long cartShoppingId, CartShoppingResource resource) {
        return new UpdateCartShoppingCommand(
                cartShoppingId,
                resource.userClientId(),
                resource.productId(),
                resource.productName(),
                resource.productPrice(),
                resource.quantity(),
                resource.statusCartShoppingItem()
        );
    }
}

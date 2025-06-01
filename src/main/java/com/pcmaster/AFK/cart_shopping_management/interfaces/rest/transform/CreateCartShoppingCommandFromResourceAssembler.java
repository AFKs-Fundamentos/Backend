package com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform;

import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.CreateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources.CreateCartShoppingResource;

public class CreateCartShoppingCommandFromResourceAssembler {
    public static CreateCartShoppingCommand toCommandFromResource(CreateCartShoppingResource resource){
        return new CreateCartShoppingCommand(
                resource.userClientId(),
                resource.productId(),
                resource.productName(),
                resource.productPrice(),
                resource.quantity(),
                resource.statusCartShoppingItem()
        );

    }
}

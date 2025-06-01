package com.pcmaster.AFK.cart_shopping_management.domain.services;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.CreateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.DeleteCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.UpdateCartShoppingCommand;

import java.util.Optional;

public interface CartShoppingCommandService {
    Long handle(CreateCartShoppingCommand command);
    Optional<CartShopping> handle(UpdateCartShoppingCommand command);
    void handle(DeleteCartShoppingCommand command);
}

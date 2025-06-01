package com.pcmaster.AFK.cart_shopping_management.domain.services;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByIdQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByUserClientIdAndStatusQuery;

import java.util.List;
import java.util.Optional;

public interface CartShoppingQueryService {
    Optional<CartShopping> handle(GetCartShoppingByIdQuery query);
    List<CartShopping> handle(GetCartShoppingByUserClientIdAndStatusQuery query);
}

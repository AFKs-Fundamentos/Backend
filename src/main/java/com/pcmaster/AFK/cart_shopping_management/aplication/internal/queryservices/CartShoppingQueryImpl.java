package com.pcmaster.AFK.cart_shopping_management.aplication.internal.queryservices;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByIdQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByUserClientIdAndStatusQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.model.valueobjects.StatusCartShoppingItem;
import com.pcmaster.AFK.cart_shopping_management.domain.services.CartShoppingQueryService;
import com.pcmaster.AFK.cart_shopping_management.infrastructure.persistence.jpa.repositories.CartShoppingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartShoppingQueryImpl implements CartShoppingQueryService {
    private final CartShoppingRepository cartShoppingRepository;

    public CartShoppingQueryImpl(CartShoppingRepository cartShoppingRepository) {
        this.cartShoppingRepository = cartShoppingRepository;
    }

    @Override
    public Optional<CartShopping> handle(GetCartShoppingByIdQuery query) {
        return this.cartShoppingRepository.findById(query.cartShoppingId());
    }

    @Override
    public List<CartShopping> handle(GetCartShoppingByUserClientIdAndStatusQuery query) {
        StatusCartShoppingItem statusCartShoppingItem= StatusCartShoppingItem.valueOf(query.statusCartShopping().toUpperCase());
        return this.cartShoppingRepository.findByUserClientIdAndStatusCartShoppingItem( query.userClientId(),statusCartShoppingItem);
    }
}

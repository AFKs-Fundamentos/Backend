package com.pcmaster.AFK.cart_shopping_management.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.domain.model.valueobjects.StatusCartShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartShoppingRepository extends JpaRepository<CartShopping, Long> {
    List<CartShopping> findByUserClientIdAndStatusCartShoppingItem(Long userClientId,StatusCartShoppingItem statusCartShoppingItem );
}

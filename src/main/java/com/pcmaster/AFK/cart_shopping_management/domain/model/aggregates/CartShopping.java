package com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates;

import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.CreateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.valueobjects.StatusCartShoppingItem;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cart_shoppings")
public class CartShopping extends AuditableAbstractAggregateRoot<CartShopping> {

    private Long userClientId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private int quantity;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status_cart_shopping_item", length = 15, nullable = false)
    private StatusCartShoppingItem statusCartShoppingItem;

    public CartShopping() {}

    public CartShopping(CreateCartShoppingCommand command) {
        this.userClientId = command.userClientId();
        this.productId = command.productId();
        this.productName = command.productName();
        this.productPrice = command.productPrice();
        this.quantity = command.quantity();
        this.statusCartShoppingItem = StatusCartShoppingItem.valueOf(command.statusCartItem());

    }

    public void updateCartShoppingItemInformation(
            Long userClientId,
            Long productId,
            String productName,
            Double productPrice,
            int quantity,
            String statusCartShoppingItem
    ){
        this.userClientId = userClientId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.statusCartShoppingItem = StatusCartShoppingItem.valueOf(statusCartShoppingItem);
    }


}

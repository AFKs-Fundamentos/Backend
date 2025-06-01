package com.pcmaster.AFK.cart_shopping_management.aplication.internal.commandservices;

import com.pcmaster.AFK.cart_shopping_management.domain.model.aggregates.CartShopping;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.CreateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.DeleteCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.UpdateCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.services.CartShoppingCommandService;
import com.pcmaster.AFK.cart_shopping_management.infrastructure.persistence.jpa.repositories.CartShoppingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartShoppingCommandImpl implements CartShoppingCommandService {

    private final CartShoppingRepository cartShoppingRepository;

    public CartShoppingCommandImpl(CartShoppingRepository cartShoppingRepository) {
        this.cartShoppingRepository = cartShoppingRepository;
    }

    @Override
    public Long handle(CreateCartShoppingCommand command) {

        var cartShopping = new CartShopping(command);

        try{
            this.cartShoppingRepository.save(cartShopping);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving cart shopping: " + e.getMessage());
        }

        return cartShopping.getId();
    }

    @Override
    public Optional<CartShopping> handle(UpdateCartShoppingCommand command) {

        var cartShoppingId = command.cartShoppingId();
        if (!this.cartShoppingRepository.existsById(cartShoppingId)) {
            throw new IllegalArgumentException("Cart shopping with id " + cartShoppingId + " does not exist");
        }

        var cartShoppingToUpdate = this.cartShoppingRepository.findById(cartShoppingId).get();
        cartShoppingToUpdate.updateCartShoppingItemInformation(
                command.userClientId(),
                command.productId(),
                command.productName(),
                command.productPrice(),
                command.quantity(),
                command.statusCartItem()
        );

        try{
            var updatedCartShopping = this.cartShoppingRepository.save(cartShoppingToUpdate);
            return Optional.of(updatedCartShopping);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while UPDATING cart shopping: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeleteCartShoppingCommand command) {
        if (!this.cartShoppingRepository.existsById(command.cartShoppingId())) {
            throw new IllegalArgumentException("Cart shopping with ID " + command.cartShoppingId() + " does NOT EXIST");
        }
        try {
            this.cartShoppingRepository.deleteById(command.cartShoppingId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while DELETING cart shopping: " + e.getMessage());
        }
    }
}

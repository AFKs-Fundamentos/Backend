package com.pcmaster.AFK.cart_shopping_management.interfaces.acl;

import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByIdQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.services.CartShoppingQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartShoppingContextFacade {
    private final CartShoppingQueryService cartShoppingQueryService;

    public CartShoppingContextFacade(CartShoppingQueryService cartShoppingQueryService) {
        this.cartShoppingQueryService = cartShoppingQueryService;
    }

    /**
     * Retrieves the total price of a cart shopping item by its ID.
     * @param cartShoppingId the ID of the cart shopping item
     * @return the total price of the cart shopping item, or null if not found
     */
    public Double fetchTotalPriceByCartShoppingId(Long cartShoppingId) {
        var getCartShoppingByIdQuery = new GetCartShoppingByIdQuery(cartShoppingId);
        var optionalCartShopping = cartShoppingQueryService.handle(getCartShoppingByIdQuery);
        return optionalCartShopping.map(cartShopping -> cartShopping.getProductPrice() * cartShopping.getQuantity()).orElse(null);
    }

    /**
     * Retrieves the product name by cart shopping ID.
     * @param cartShoppingId the ID of the cart shopping item whose product name is to be retrieved
     * @return an Optional containing the name of the product associated with the specified cart shopping ID, or empty if not found
     */
    public Optional<String> fetchProductNameByCartShoppingId(Long cartShoppingId) {
        var getCartShoppingByIdQuery = new GetCartShoppingByIdQuery(cartShoppingId);
        var optionalCartShopping = cartShoppingQueryService.handle(getCartShoppingByIdQuery);
        Optional<String> productName = optionalCartShopping.map(cartShopping -> cartShopping.getProductName());
        return productName;
    }
}

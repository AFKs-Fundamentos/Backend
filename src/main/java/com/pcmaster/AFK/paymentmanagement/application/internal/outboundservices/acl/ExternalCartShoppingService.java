package com.pcmaster.AFK.paymentmanagement.application.internal.outboundservices.acl;

import com.pcmaster.AFK.cart_shopping_management.interfaces.acl.CartShoppingContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalCartShoppingService {
    private final CartShoppingContextFacade cartShoppingContextFacade;

    public ExternalCartShoppingService(CartShoppingContextFacade cartShoppingContextFacade) {
        this.cartShoppingContextFacade = cartShoppingContextFacade;
    }

    public Double fetchTotalPriceByCartShoppingId(Long cartShoppingId) {
        return cartShoppingContextFacade.fetchTotalPriceByCartShoppingId(cartShoppingId);
    }

    public Optional<String> fetchProductNameByCartShoppingId(Long cartShoppingId) {
        return cartShoppingContextFacade.fetchProductNameByCartShoppingId(cartShoppingId);
    }
}

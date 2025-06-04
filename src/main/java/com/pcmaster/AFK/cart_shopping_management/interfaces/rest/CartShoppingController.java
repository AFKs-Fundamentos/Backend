package com.pcmaster.AFK.cart_shopping_management.interfaces.rest;
import com.pcmaster.AFK.cart_shopping_management.domain.model.commands.DeleteCartShoppingCommand;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByIdQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.model.queries.GetCartShoppingByUserClientIdAndStatusQuery;
import com.pcmaster.AFK.cart_shopping_management.domain.services.CartShoppingCommandService;
import com.pcmaster.AFK.cart_shopping_management.domain.services.CartShoppingQueryService;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources.CartShoppingResource;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.resources.CreateCartShoppingResource;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform.CartShoppingResourceFromEntityAssembler;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform.CreateCartShoppingCommandFromResourceAssembler;
import com.pcmaster.AFK.cart_shopping_management.interfaces.rest.transform.UpdateCartShoppingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "**",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/cartShoppings",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CartShopping",description = "Cart Shopping Management Endpoints")
public class CartShoppingController {
    private final CartShoppingQueryService cartShoppingQueryService;
    private final CartShoppingCommandService cartShoppingCommandService;

    public CartShoppingController(CartShoppingQueryService cartShoppingQueryService, CartShoppingCommandService cartShoppingCommandService) {
        this.cartShoppingQueryService = cartShoppingQueryService;
        this.cartShoppingCommandService = cartShoppingCommandService;
    }

    /**
     * Creates a new cart shopping.
     *
     * @param resource the resource containing cart shopping details
     * @return ResponseEntity with the created CartShoppingResource and HTTP status
     */
    @PostMapping
    public ResponseEntity<CartShoppingResource> create(@RequestBody CreateCartShoppingResource resource) {
        var createCartShoppingCommand = CreateCartShoppingCommandFromResourceAssembler.toCommandFromResource(resource);
        var cartShoppingId = this.cartShoppingCommandService.handle(createCartShoppingCommand);
        if (cartShoppingId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }
        var getCartShoppingByIdQuery = new GetCartShoppingByIdQuery(cartShoppingId);
        var optionalCartShopping = this.cartShoppingQueryService.handle(getCartShoppingByIdQuery);
        var cartShoppingResource = CartShoppingResourceFromEntityAssembler.toResourceFromEntity(optionalCartShopping.get());
        return new ResponseEntity<>(cartShoppingResource, HttpStatus.CREATED);
    }
    /**
     * Updates an existing cart shopping.
     *
     * @param resource the resource containing updated cart shopping details
     * @return ResponseEntity with the updated CartShoppingResource and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<CartShoppingResource> update(@PathVariable Long id, @RequestBody CartShoppingResource resource) {
        var updateCartShoppingCommand = UpdateCartShoppingCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var optionalCartShopping = this.cartShoppingCommandService.handle(updateCartShoppingCommand);
        if (optionalCartShopping.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cartShoppingResource = CartShoppingResourceFromEntityAssembler.toResourceFromEntity(optionalCartShopping.get());
        return ResponseEntity.ok(cartShoppingResource);
    }
    /**
     * Deletes a cart shopping by its ID.
     *
     * @param id the ID of the cart shopping to delete
     * @return ResponseEntity with HTTP status indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var deleteCartShoppingCommand = new DeleteCartShoppingCommand(id);
        this.cartShoppingCommandService.handle(deleteCartShoppingCommand);
        return ResponseEntity.noContent().build();
    }
    //######################Query Methods######################
    /**
     * Retrieves a cart shopping by its ID.
     *
     * @param id the ID of the cart shopping to retrieve
     * @return ResponseEntity with CartShoppingResource if found, or bad request if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CartShoppingResource> getById(@PathVariable Long id) {
        var getCartShoppingByIdQuery = new GetCartShoppingByIdQuery(id);
        var optionalCartShopping = this.cartShoppingQueryService.handle(getCartShoppingByIdQuery);
        if (optionalCartShopping.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cartShoppingResource = CartShoppingResourceFromEntityAssembler.toResourceFromEntity(optionalCartShopping.get());
        return ResponseEntity.ok(cartShoppingResource);
    }
    /**
     * Retrieves a card shopping by StatusCartShoppingItem and userClientId.
     *
     * @param statusCartShoppingItem the status of the cart shopping item
     * @param userClientId the ID of the user client
     * @return ResponseEntity with a list of CartShoppingResource matching the criteria
     */
    @GetMapping("/userClientId/{userClientId}/statusCartShoppingItem/{statusCartShoppingItem}")
    public ResponseEntity<List<CartShoppingResource>> getByStatusCartShoppingItemAndUserClientId(@PathVariable Long userClientId,@PathVariable String statusCartShoppingItem ) {
        var getCartShoppingByUserClientIdAndStatusCartShoppingItemQuery = new GetCartShoppingByUserClientIdAndStatusQuery(userClientId,statusCartShoppingItem);
        var cartShoppings = this.cartShoppingQueryService.handle(getCartShoppingByUserClientIdAndStatusCartShoppingItemQuery);
        if (cartShoppings.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cartShoppingResources = cartShoppings.stream()
                .map(CartShoppingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartShoppingResources);
    }
}
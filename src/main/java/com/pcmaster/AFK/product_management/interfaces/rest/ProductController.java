package com.pcmaster.AFK.product_management.interfaces.rest;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteProductCommand;
import com.pcmaster.AFK.product_management.domain.model.queries.GetAllProductQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetProductByIdQuery;
import com.pcmaster.AFK.product_management.domain.services.ProductCommandService;
import com.pcmaster.AFK.product_management.domain.services.ProductQueryService;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.CreateProductResource;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.ProductResource;
import com.pcmaster.AFK.product_management.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.pcmaster.AFK.product_management.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.pcmaster.AFK.product_management.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "**", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoints")
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }
    @PostMapping
    public ResponseEntity<ProductResource> create(@RequestBody CreateProductResource resource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var productId = this.productCommandService.handle(createProductCommand);
        if (productId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var optionalProduct = this.productQueryService.handle(getProductByIdQuery);
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(optionalProduct.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }
    /**
     * Updates an existing product by ID.
     * @param resource the resource containing updated product details
     * @return ResponseEntity with the updated ProductResource and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> update(@PathVariable("id") Long id, @RequestBody ProductResource resource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalProduct = this.productCommandService.handle(updateProductCommand);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(optionalProduct.get());
        return ResponseEntity.ok(productResource);
    }
    /**
     * Deletes a product by ID.
     * @param id the ID of the product to be deleted
     * @return ResponseEntity with HTTP status indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var deleteProductCommand = new DeleteProductCommand(id);
        this.productCommandService.handle(deleteProductCommand);
        return ResponseEntity.noContent().build();
    }
    //#######################Query Methods#########################
    /**
     * Retrieves all products.
     * @return ResponseEntity with a list of ProductResource and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<ProductResource>> getAll(){
        var getAllProductsQuery = new GetAllProductQuery();
        var products = this.productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResources);
    }
    /**
     * Retrieves a product by its ID.
     * @param id the ID of the product to be retrieved
     * @return ResponseEntity with the ProductResource and HTTP status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        var getProductByIdQuery = new GetProductByIdQuery(id);
        var optionalProduct = this.productQueryService.handle(getProductByIdQuery);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(optionalProduct.get());
        return ResponseEntity.ok(productResource);
    }
}

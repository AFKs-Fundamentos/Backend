package com.pcmaster.AFK.product_management.interfaces.acl;

import com.pcmaster.AFK.product_management.domain.model.queries.GetProductByIdQuery;
import com.pcmaster.AFK.product_management.domain.services.ProductQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductContextFacade {
    private final ProductQueryService productQueryService;

    public ProductContextFacade(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }
    /**
     * Retrieves the product name by product ID.
     *
     * @param productId the ID of the product whose name is to be retrieved
     * @return an Optional containing the name of the product associated with the specified product ID, or empty if not found
     */
    public Optional<String> fetchProductNameById(Long productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var optionalProduct = productQueryService.handle(getProductByIdQuery);
        Optional<String> productName = optionalProduct.map(product -> product.getProductName());
        return productName;
    }
}

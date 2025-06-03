package com.pcmaster.AFK.product_management.interfaces.acl;

import com.pcmaster.AFK.product_management.domain.model.queries.GetProductByIdQuery;
import com.pcmaster.AFK.product_management.domain.services.ProductQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProductContextFacade {
    private final ProductQueryService productQueryService;

    public ProductContextFacade(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

}

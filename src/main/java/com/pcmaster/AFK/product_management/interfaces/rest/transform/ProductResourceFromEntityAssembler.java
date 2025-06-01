package com.pcmaster.AFK.product_management.interfaces.rest.transform;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity){
        return new ProductResource(
                entity.getId(),
                entity.getProductName(),
                entity.getPhoto(),
                entity.getSku(),
                entity.getCategory(),
                entity.getPrice(),
                entity.getDescription()
        );
    }
}

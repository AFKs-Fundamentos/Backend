package com.pcmaster.AFK.product_managment.domain.services;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_managment.domain.model.queries.GetAllProductQuery;
import com.pcmaster.AFK.product_managment.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
}

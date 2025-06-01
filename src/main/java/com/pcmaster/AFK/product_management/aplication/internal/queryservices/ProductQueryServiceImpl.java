package com.pcmaster.AFK.product_management.aplication.internal.queryservices;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_management.domain.model.queries.GetAllProductQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetProductByIdQuery;
import com.pcmaster.AFK.product_management.domain.services.ProductQueryService;
import com.pcmaster.AFK.product_management.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductQuery query) {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query){
        return this.productRepository.findById(query.productId());
    }
}

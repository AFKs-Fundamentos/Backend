package com.pcmaster.AFK.product_management.aplication.internal.queryservices;

import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByProductIdQuery;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.ProductId;
import org.springframework.stereotype.Service;
import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByIdQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByUserIdQuery;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.UserTechnicalId;
import com.pcmaster.AFK.product_management.domain.services.InventoryQueryService;
import com.pcmaster.AFK.product_management.infrastructure.persistence.jpa.repositories.InventoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    private final InventoryRepository inventoryRepository;

    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public Optional<Inventory> handle(GetInventoryByIdQuery query) {
        return this.inventoryRepository.findById(query.inventoryId());
    }

    @Override
    public List<Inventory> handle(GetInventoryByUserIdQuery query) {
        return this.inventoryRepository.findByUserTechnicalId(new UserTechnicalId(query.userTechnicalId()));
    }

    @Override
    public Optional<Inventory> handle(GetInventoryByProductIdQuery query) {
        return this.inventoryRepository.findByProductId(new ProductId(query.productId()));
    }
}

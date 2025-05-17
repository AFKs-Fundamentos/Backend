package com.pcmaster.AFK.product_managment.aplication.internal.queryservices;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_managment.domain.model.queries.GetInventoryByUserId;
import com.pcmaster.AFK.product_managment.domain.services.InventoryQueryService;
import com.pcmaster.AFK.product_managment.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    private final InventoryRepository inventoryRepository;

    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> handle(GetInventoryByUserId query) {
        return this.inventoryRepository.findByUser_id(query.userId());
    }
}

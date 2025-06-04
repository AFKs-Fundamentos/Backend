package com.pcmaster.AFK.product_management.domain.services;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByIdQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByProductIdQuery;
import com.pcmaster.AFK.product_management.domain.model.queries.GetInventoryByUserIdQuery;


import java.util.List;
import java.util.Optional;


public interface InventoryQueryService {
    Optional<Inventory> handle(GetInventoryByIdQuery query);
    List<Inventory> handle(GetInventoryByUserIdQuery query);
    Optional<Inventory> handle(GetInventoryByProductIdQuery query);
}

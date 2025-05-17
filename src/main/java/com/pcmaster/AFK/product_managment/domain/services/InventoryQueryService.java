package com.pcmaster.AFK.product_managment.domain.services;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_managment.domain.model.queries.GetInventoryByUserId;

import java.util.List;


public interface InventoryQueryService {

    List<Inventory> handle(GetInventoryByUserId query);
}

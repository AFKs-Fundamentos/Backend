package com.pcmaster.AFK.product_management.interfaces.rest.transform;


import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public static InventoryResource toResourceFromEntity(Inventory entity){
        return new InventoryResource(
                entity.getId(),
                entity.getUserTechnicalId().getValue(),
                entity.getProductId().getValue(),
                entity.getStock(),
                entity.getStockMin(),
                entity.getStockMax()
        );
    }
}

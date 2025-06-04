package com.pcmaster.AFK.product_management.aplication.internal.outboundservices.acl;

import com.pcmaster.AFK.product_management.interfaces.acl.InventoryContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalInventoryService {
    private final InventoryContextFacade inventoryContextFacade;

    public ExternalInventoryService(InventoryContextFacade inventoryContextFacade) {
        this.inventoryContextFacade = inventoryContextFacade;
    }

    /**
     * Deletes an inventory by product ID.
     *
     * @param productId the ID of the product whose inventory is to be deleted
     */
    public void deleteInventoryByProductId(Long productId) {
        inventoryContextFacade.deleteInventoryByProductId(productId);
    }
    /**
     * Checks if an inventory exists by product ID.
     *
     * @param productId the ID of the product to check
     * @return true if the inventory exists, false otherwise
     */
    public boolean inventoryExistsByProductId(Long productId) {
        return inventoryContextFacade.inventoryExistsByProductId(productId);
    }


}

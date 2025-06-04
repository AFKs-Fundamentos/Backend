package com.pcmaster.AFK.product_management.domain.services;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryByProductIdCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.UpdateInventoryCommand;

import java.util.Optional;

public interface InventoryCommandService {
    Long handle(CreateInventoryCommand command);
    Optional<Inventory> handle(UpdateInventoryCommand command);
    void handle(DeleteInventoryCommand command);
    void handle(DeleteInventoryByProductIdCommand command);
}

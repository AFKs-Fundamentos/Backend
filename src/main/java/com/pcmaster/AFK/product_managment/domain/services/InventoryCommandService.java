package com.pcmaster.AFK.product_managment.domain.services;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_managment.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.DeleteInventoryCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.UpdateInventoryCommand;

import java.util.Optional;

public interface InventoryCommandService {
    Long handle(CreateInventoryCommand command);
    Optional<Inventory> handle(UpdateInventoryCommand command);
    void handle(DeleteInventoryCommand command);
}

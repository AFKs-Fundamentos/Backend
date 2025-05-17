package com.pcmaster.AFK.product_managment.aplication.internal.commandservices;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_managment.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.DeleteInventoryCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.UpdateInventoryCommand;
import com.pcmaster.AFK.product_managment.domain.services.InventoryCommandService;
import com.pcmaster.AFK.product_managment.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements  InventoryCommandService {
    private final InventoryRepository inventoryRepository;
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Long handle(CreateInventoryCommand command) {
        var inventory = new Inventory(command);

        try {
            inventory= inventoryRepository.save(inventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving inventory: " + e.getMessage());
        }
        return inventory.getId();
    }

    @Override
    public Optional<Inventory> handle(UpdateInventoryCommand command) {
        var inventoryId = command.inventoryId();

        if (!this.inventoryRepository.existsById(inventoryId)) {
            throw new IllegalArgumentException("Inventory with id " + inventoryId + " does not exist");
        }
        var inventoryToUpdate = this.inventoryRepository.findById(inventoryId).get();
        inventoryToUpdate.updateInformation(
                command.product_id(),
                command.user_id()
        );

        try {
            var updateInventory = this.inventoryRepository.save(inventoryToUpdate);
            return Optional.of(updateInventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating inventory: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeleteInventoryCommand command) {
        var inventoryId = command.inventoryId();

        if (!this.inventoryRepository.existsById(inventoryId)) {
            throw new IllegalArgumentException("Inventory with id " + inventoryId + " does not exist");
        }

        try {
            this.inventoryRepository.deleteById(inventoryId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting inventory: " + e.getMessage());
        }

    }
}

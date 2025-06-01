package com.pcmaster.AFK.product_management.aplication.internal.commandservices;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryByProductIdCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.UpdateInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.ProductId;
import com.pcmaster.AFK.product_management.domain.services.InventoryCommandService;
import com.pcmaster.AFK.product_management.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements  InventoryCommandService {
    private final InventoryRepository inventoryRepository;
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public Long handle(CreateInventoryCommand command) {
        if (this.inventoryRepository.existsByProductId(new ProductId(command.productId()))) {
            throw new IllegalArgumentException("Inventory with this product already exists.");
        }
        var inventory = new Inventory(command);
        try {
            this.inventoryRepository.save(inventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving inventory: " + e.getMessage());
        }

        return inventory.getId();
    }

    @Override
    public Optional<Inventory> handle(UpdateInventoryCommand command) {
        var inventoryId = command.inventoryId();

        if(!this.inventoryRepository.existsById(inventoryId)) {
            throw new IllegalArgumentException("Inventory with id " + inventoryId + " does not exist");
        }

        // Verificar si el ProductId ya existe en otro inventario
        var existingInventory = this.inventoryRepository.findByProductId(new ProductId(command.productId()));
        if (existingInventory.isPresent() && !existingInventory.get().getId().equals(inventoryId)) {
            throw new IllegalArgumentException("Inventory with this product already exists.");
        }

        var inventoryToUpdate = this.inventoryRepository.findById(inventoryId).get();

        inventoryToUpdate.updateInventoryDetailsInformation(
                command.userTechnicalId(),
                command.productId(),
                command.stock(),
                command.stockMin(),
                command.stockMax()
        );

        try{
            var updatedInventory = this.inventoryRepository.save(inventoryToUpdate);
            return Optional.of(updatedInventory);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating inventory: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteInventoryCommand command) {
        if(!this.inventoryRepository.existsById(command.inventoryId())) {
            throw new IllegalArgumentException("Inventory with id " + command.inventoryId() + " does not exist");
        }
        try {
            this.inventoryRepository.deleteById(command.inventoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting inventory: " + e.getMessage());
        }
    }

    @Transactional
    public void handle(DeleteInventoryByProductIdCommand command) {
        var productId = new ProductId(command.productId());
        if(!this.inventoryRepository.existsByProductId(productId)) {
            throw new IllegalArgumentException("Inventory with product id " + productId + " does not exist");
        }
        try{
            this.inventoryRepository.deleteByProductId(productId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting inventory by product id: " + e.getMessage());
        }

    }
}

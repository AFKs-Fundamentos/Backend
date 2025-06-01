package com.pcmaster.AFK.product_management.domain.services;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_management.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteProductCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}

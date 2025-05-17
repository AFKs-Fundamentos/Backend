package com.pcmaster.AFK.product_managment.domain.services;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_managment.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.DeleteProductCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}

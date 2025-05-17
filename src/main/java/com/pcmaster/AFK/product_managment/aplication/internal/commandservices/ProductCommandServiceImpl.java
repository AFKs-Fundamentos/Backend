package com.pcmaster.AFK.product_managment.aplication.internal.commandservices;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_managment.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.DeleteProductCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.UpdateProductCommand;
import com.pcmaster.AFK.product_managment.domain.services.ProductCommandService;
import com.pcmaster.AFK.product_managment.infrastructure.persistence.jpa.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        var product = new Product(command);
        try{
            product= productRepository.save(product);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving product: " + e.getMessage());
        }
        return product.getId();
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        var productId = command.productId();

        if (!this.productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with id " + productId + " does not exist");
        }

        var productToUpdate = this.productRepository.findById(productId).get();
        productToUpdate.updateInformation(
                command.productName(),
                command.photo(),
                command.sku(),
                command.category(),
                command.price(),
                command.stock(),
                command.description(),
                command.isFavorite(),
                command.characteristics_id()
                );
        try {
           var updateProduct=  this.productRepository.save(productToUpdate);
            return Optional.of(updateProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());

        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        var productId = command.productId();

        if (!this.productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with id " + productId + " does not exist");
        }

        try {
            this.productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage());
        }
    }

}

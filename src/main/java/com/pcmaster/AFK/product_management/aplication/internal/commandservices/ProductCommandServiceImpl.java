package com.pcmaster.AFK.product_management.aplication.internal.commandservices;

import com.pcmaster.AFK.product_management.aplication.internal.outboundservices.acl.ExternalInventoryService;
import com.pcmaster.AFK.product_management.domain.model.aggregates.Product;
import com.pcmaster.AFK.product_management.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.DeleteProductCommand;
import com.pcmaster.AFK.product_management.domain.model.commands.UpdateProductCommand;
import com.pcmaster.AFK.product_management.domain.services.ProductCommandService;
import com.pcmaster.AFK.product_management.infrastructure.persistence.jpa.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final ExternalInventoryService externalInventoryService;

    public ProductCommandServiceImpl(ProductRepository productRepository, ExternalInventoryService externalInventoryService) {
        this.productRepository = productRepository;
        this.externalInventoryService = externalInventoryService;
    }


    @Override
    public Long handle(CreateProductCommand command) {


        if (this.productRepository.findBySku(command.sku()).isPresent()) {
            throw new IllegalArgumentException("Product with this SKU already exists.");
        }

       var product = new Product(command);

       try {
              this.productRepository.save(product);
       } catch (Exception e) {
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
        if (this.productRepository.findBySku(command.sku()).filter(product -> !product.getId().equals(command.productId())).isPresent()) {
            throw new IllegalArgumentException("Product with this SKU already exists.");
        }
        var productToUpdate = this.productRepository.findById(productId).get();
        productToUpdate.updateProductInformationDetails(
                command.productName(),
                command.photo(),
                command.sku(),
                command.category(),
                command.price(),
                command.description()
        );
        try {
            var updatedProduct = this.productRepository.save(productToUpdate);
            return Optional.of(updatedProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if (!this.productRepository.existsById(command.productId())) {
            throw new EntityNotFoundException("Product with id " + command.productId() + " does not exist");
        }
        try {
            this.productRepository.deleteById(command.productId());
            // Delete the inventory associated with the product
            //this.externalInventoryService.deleteInventoryByProductId(command.productId());

        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage());
        }

    }
}

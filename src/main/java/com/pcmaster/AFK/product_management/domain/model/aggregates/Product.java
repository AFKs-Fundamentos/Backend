package com.pcmaster.AFK.product_management.domain.model.aggregates;

import com.pcmaster.AFK.product_management.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table (name = "products")
public class Product extends AuditableAbstractAggregateRoot<Product> {
    private String productName;
    private String photo;
    private String sku;
    private String category;
    private Double price;

    private String description;


    public Product() {
    }
    public Product(CreateProductCommand command) {
        this.productName = command.productName();
        this.photo = command.photo();
        this.sku = command.sku();
        this.category = command.category();
        this.price = command.price();

        this.description = command.description();

    }

    public void updateProductInformationDetails(
            String productName,
            String photo,
            String sku,
            String category,
            Double price,
            String description
    ) {
        this.productName = productName;
        this.photo = photo;
        this.sku = sku;
        this.category = category;
        this.price = price;
        this.description = description;
    }


}

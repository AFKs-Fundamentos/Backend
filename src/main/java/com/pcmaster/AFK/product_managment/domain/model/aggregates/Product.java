package com.pcmaster.AFK.product_managment.domain.model.aggregates;

import com.pcmaster.AFK.product_managment.domain.model.commands.CreateProductCommand;
import com.pcmaster.AFK.product_managment.domain.model.commands.UpdateProductCommand;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table (name = "products")
public class Product extends AuditableAbstractAggregateRoot<Product> {
    private String productName;
    private String photo;
    private String sku;
    private String category;
    private Float price;
    private Long stock;
    private String description;
    private Boolean isFavorite;
    private Long characteristics_id;

    public Product() {
    }
    public Product(CreateProductCommand command) {
        this.productName = command.productName();
        this.photo = command.photo();
        this.sku = command.sku();
        this.category = command.category();
        this.price = command.price();
        this.stock = command.stock();
        this.description = command.description();
        this.isFavorite = command.isFavorite();
        this.characteristics_id = command.characteristics_id();
    }

    public void updateInformation(
            String productName,
            String photo,
            String sku,
            String category,
            Float price,
            Long stock,
            String description,
            Boolean isFavorite,
            Long characteristics_id
    ) {
        this.productName = productName;
        this.photo = photo;
        this.sku = sku;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.isFavorite = isFavorite;
        this.characteristics_id = characteristics_id;
    }


}

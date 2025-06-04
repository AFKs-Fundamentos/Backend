package com.pcmaster.AFK.product_management.domain.model.aggregates;


import com.pcmaster.AFK.product_management.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.ProductId;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.UserTechnicalId;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "inventories")
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userTechnicalId", column = @jakarta.persistence.Column(name = "user_technical_id", nullable = false))
    })
    private UserTechnicalId userTechnicalId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "productId", column = @jakarta.persistence.Column(name = "product_id", nullable = false))
    })
    private ProductId productId;

    private Long stock;
    private Long stockMin;
    private Long stockMax;



    public Inventory() {}



    public Inventory(CreateInventoryCommand command) {
        this.userTechnicalId = new UserTechnicalId(command.userTechnicalId());
        this.productId = new ProductId(command.productId());
        this.stock = command.stock();
        this.stockMin = command.stockMin();
        this.stockMax = command.stockMax();
    }

    public void updateInventoryDetailsInformation(
            Long  userTechnicalId,
            Long productId,
            Long stock,
            Long stockMin,
            Long stockMax
    ) {
        this.userTechnicalId = new UserTechnicalId(userTechnicalId);
        this.productId = new ProductId(productId);
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
    }
}

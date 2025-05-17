package com.pcmaster.AFK.product_managment.domain.model.aggregates;


import com.pcmaster.AFK.product_managment.domain.model.commands.CreateInventoryCommand;
import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "inventories")
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    private Long products_id;
    private Long user_id;

    public Inventory() {

    }
    public Inventory(CreateInventoryCommand command) {
        this.products_id= command.product_id();
        this.user_id = command.user_id();
    }

    public void updateInformation(
            Long products_id,
            Long user_id
    ) {
        this.products_id = products_id;
        this.user_id = user_id;
    }
}

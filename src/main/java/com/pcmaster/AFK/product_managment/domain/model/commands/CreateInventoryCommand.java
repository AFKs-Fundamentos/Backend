package com.pcmaster.AFK.product_managment.domain.model.commands;

public record CreateInventoryCommand(
        Long product_id,
        Long user_id
) {
}

package com.pcmaster.AFK.product_managment.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.product_managment.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByUser_id(Long userId);
}

package com.pcmaster.AFK.product_management.infrastructure.persistence.jpa.repositories;

import com.pcmaster.AFK.product_management.domain.model.aggregates.Inventory;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.ProductId;
import com.pcmaster.AFK.product_management.domain.model.valueobjects.UserTechnicalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByProductId(ProductId productId);
    List<Inventory> findByUserTechnicalId(UserTechnicalId userTechnicalId);
    Optional<Inventory> findByProductId(ProductId productId);

    void deleteByProductId(ProductId productId);
}

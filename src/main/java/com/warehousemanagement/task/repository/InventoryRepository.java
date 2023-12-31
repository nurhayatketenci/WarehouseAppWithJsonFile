package com.warehousemanagement.task.repository;

import com.warehousemanagement.task.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    boolean existsByArtIdAndStockGreaterThanEqual(Long artId, int stock);
    Inventory findByArtId(Long artId);
}

package com.warehousemanagement.task.repository;

import com.warehousemanagement.task.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

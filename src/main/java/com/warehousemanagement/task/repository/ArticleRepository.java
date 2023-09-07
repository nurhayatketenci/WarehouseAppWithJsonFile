package com.warehousemanagement.task.repository;

import com.warehousemanagement.task.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Articles,Long> {
}

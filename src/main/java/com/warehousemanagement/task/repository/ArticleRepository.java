package com.warehousemanagement.task.repository;

import com.warehousemanagement.task.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}

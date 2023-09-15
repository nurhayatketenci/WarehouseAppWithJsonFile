package com.warehousemanagement.task.service;

import com.warehousemanagement.task.model.Article;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.repository.ArticleRepository;
import com.warehousemanagement.task.repository.InventoryRepository;
import com.warehousemanagement.task.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    private ProductRepository productRepository;
    private ArticleRepository articleRepository;
    private InventoryRepository inventoryRepository;


    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        inventoryRepository = Mockito.mock(InventoryRepository.class);
        articleRepository = Mockito.mock(ArticleRepository.class);

    }

    @DisplayName("should Return Product With Product And Updated ProductId when ProductId  Exist")
    @Test
    void shouldReturnProductWithProductAndUpdatedProductId_whenProductIdExist(){


    }


    @AfterEach
    public void tearDown() {

    }

}
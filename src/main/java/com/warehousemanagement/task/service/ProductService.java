package com.warehousemanagement.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehousemanagement.task.dto.BulkProductSaveRequest;
import com.warehousemanagement.task.exception.NotFoundException;
import com.warehousemanagement.task.model.Articles;
import com.warehousemanagement.task.model.Inventory;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.repository.ArticleRepository;
import com.warehousemanagement.task.repository.InventoryRepository;
import com.warehousemanagement.task.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ArticleRepository articleRepository;
    private InventoryRepository inventoryRepository;
    private ObjectMapper objectMapper;

    public ProductService(ProductRepository productRepository, ArticleRepository articleRepository, InventoryRepository inventoryRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.inventoryRepository = inventoryRepository;
        this.objectMapper = objectMapper;
    }
    public void saveProduct(MultipartFile file) throws IOException {
        String productJson = new String(file.getBytes(), StandardCharsets.UTF_8);
        BulkProductSaveRequest productSaveRequest = objectMapper.readValue(productJson, BulkProductSaveRequest.class);
        List<Product> products=productSaveRequest.getProducts();
        for(Product product : products){
                List<Articles> containArticles = product.getArticles();
                for (Articles article : containArticles) {
                    this.articleRepository.save(article);
                }
                this.productRepository.save(product);
        }
    }


    public List<Product> getAll(){
        return this.productRepository.findAll();
    }


    public Product sellProduct(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        List<Articles> containArticles = product.getArticles();
        for (Articles article : containArticles) {
            Inventory inventory = this.inventoryRepository.findByArtId(article.getArtId());
            boolean status = this.inventoryRepository.existsByArtIdAndStockGreaterThanEqual(article.getArtId(), article.getAmountOf());
            if (status) {
                int newStock = inventory.getStock() - article.getAmountOf();
                inventory.setStock(newStock);
                this.inventoryRepository.save(inventory);
            } else {
                throw new NotFoundException("There is not enough stock for this product");
            }
        }
        return product;
    }



}

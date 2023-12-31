package com.warehousemanagement.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehousemanagement.task.dto.BulkProductSaveRequest;
import com.warehousemanagement.task.dto.ProductResponse;
import com.warehousemanagement.task.exception.NotFoundException;
import com.warehousemanagement.task.model.Article;
import com.warehousemanagement.task.model.Inventory;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;
    private final ArticleService articleService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, InventoryService inventoryService, ArticleService articleService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    public void saveProduct(MultipartFile file) throws IOException {
        String productJson = new String(file.getBytes(), StandardCharsets.UTF_8);
        BulkProductSaveRequest productSaveRequest = objectMapper.readValue(productJson, BulkProductSaveRequest.class);
        List<Product> products = productSaveRequest.getProducts();
        for (Product product : products) {
            List<Article> containArticles = product.getArticles();
            this.articleService.saveAll(containArticles);
            this.productRepository.save(product);
        }
    }


    public List<ProductResponse> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(product ->
                        this.modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return productResponses;
    }

    public ProductResponse sellProduct(Long id) {
        Product product = findById(id);
        List<Article> containArticles = product.getArticles();
        for (Article article : containArticles) {
            Inventory inventory = this.inventoryService.findByArtId(article.getArtId());
            boolean status = this.inventoryService.existByArtIdAndStockControl(article.getArtId(), article.getAmountOf());
            if (status) {
                int newStock = inventory.getStock() - article.getAmountOf();
                inventory.setStock(newStock);
                this.inventoryService.save(inventory);
            } else {
                throw new NotFoundException("There is not enough stock for this product");
            }
        }
        ProductResponse productResponse = this.modelMapper.map(product, ProductResponse.class);
        return productResponse;
    }

    public Product findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return product;
    }


}

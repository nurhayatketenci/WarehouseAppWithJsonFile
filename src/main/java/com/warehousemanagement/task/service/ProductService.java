package com.warehousemanagement.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehousemanagement.task.dto.BulkProductSaveRequest;
import com.warehousemanagement.task.model.Articles;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.repository.ArticleRepository;
import com.warehousemanagement.task.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ArticleRepository articleRepository;
    private ObjectMapper objectMapper;

    public ProductService(ProductRepository productRepository, ArticleRepository articleRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
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

    public Optional<Product> sellProduct(Long id){
        Optional<Product> product=this.productRepository.findById(id);
        System.out.println(product.get().getArticles());
        return product;
    }
}

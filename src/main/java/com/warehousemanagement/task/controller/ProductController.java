package com.warehousemanagement.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> save(@RequestParam MultipartFile file) throws IOException {
        this.productService.saveProduct(file);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("sellproduct/{id}")
    public ResponseEntity<Product> sellProduct(@PathVariable Long id){
        this.productService.sellProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

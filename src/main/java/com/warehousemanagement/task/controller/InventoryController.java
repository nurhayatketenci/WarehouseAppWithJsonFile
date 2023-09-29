package com.warehousemanagement.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.warehousemanagement.task.model.Inventory;
import com.warehousemanagement.task.model.Product;
import com.warehousemanagement.task.service.InventoryService;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @PostMapping
    public ResponseEntity<Inventory> save(@RequestParam("file")MultipartFile file) throws IOException {
        this.inventoryService.saveInventoryForFile(file);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        List<Inventory> inventoryList = this.inventoryService.getAll();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

}

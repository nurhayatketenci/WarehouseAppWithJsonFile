package com.warehousemanagement.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehousemanagement.task.dto.BulkInventorySaveRequest;
import com.warehousemanagement.task.model.Inventory;
import com.warehousemanagement.task.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private ObjectMapper objectMapper;

    public InventoryService(InventoryRepository inventoryRepository, ObjectMapper objectMapper) {
        this.inventoryRepository = inventoryRepository;
        this.objectMapper = objectMapper;
    }

    public void saveInventoryForFile(MultipartFile file) throws IOException {
        String inventoryJson = new String(file.getBytes(), StandardCharsets.UTF_8);
        BulkInventorySaveRequest inventorySaveRequest = objectMapper.readValue(inventoryJson, BulkInventorySaveRequest.class);
        List<Inventory> inventoryList = inventorySaveRequest.getInventory();
        for (Inventory inventory : inventoryList) {
            inventoryRepository.save(inventory);
        }
    }
    public void save(Inventory inventory){
        this.inventoryRepository.save(inventory);
    }
    protected Inventory findByArtId(Long artId){
        return this.inventoryRepository.findByArtId(artId);
    }
    public boolean existByArtIdAndStockControl(Long artId, int stock){
        return this.inventoryRepository.existsByArtIdAndStockGreaterThanEqual(artId,stock);
    }

    public List<Inventory> getAll() {
        return this.inventoryRepository.findAll();
    }

}

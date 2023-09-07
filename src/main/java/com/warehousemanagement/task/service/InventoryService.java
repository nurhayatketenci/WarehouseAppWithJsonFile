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

    public void saveInventory(MultipartFile file) throws IOException {
        String inventoryJson =  new String(file.getBytes(), StandardCharsets.UTF_8);
        BulkInventorySaveRequest inventorySaveRequest = objectMapper.readValue(inventoryJson, BulkInventorySaveRequest.class);
        List<Inventory> inventoryList = inventorySaveRequest.getInventory();
        for (Inventory inventory : inventoryList) {
            inventoryRepository.save(inventory);
        }
    }

    public List<Inventory> getAll(){
        return this.inventoryRepository.findAll();
    }

}

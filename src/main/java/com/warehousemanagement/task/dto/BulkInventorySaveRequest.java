package com.warehousemanagement.task.dto;

import com.warehousemanagement.task.model.Inventory;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class BulkInventorySaveRequest {
    private List<Inventory> inventory;

}

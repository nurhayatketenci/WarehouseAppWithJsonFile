package com.warehousemanagement.task.dto;

import com.warehousemanagement.task.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkProductSaveRequest {
    private List<Product> products;
}

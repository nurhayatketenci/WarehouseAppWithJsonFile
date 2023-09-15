package com.warehousemanagement.task.dto;

import com.warehousemanagement.task.model.Article;
import com.warehousemanagement.task.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private String name;
    private List<Article> articles;
}

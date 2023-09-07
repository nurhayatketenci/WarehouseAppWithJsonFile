package com.warehousemanagement.task.dto;

import com.warehousemanagement.task.model.Articles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkArticlesSaveRequest {
    private List<Articles> articles;
}

package com.warehousemanagement.task.dto;

import com.warehousemanagement.task.model.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkArticlesSaveRequest {
    private List<Article> articles;
}

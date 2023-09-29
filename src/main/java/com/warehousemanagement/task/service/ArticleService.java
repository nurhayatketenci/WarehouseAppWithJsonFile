package com.warehousemanagement.task.service;

import com.warehousemanagement.task.model.Article;
import com.warehousemanagement.task.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public void saveAll(List<Article> containArticles){
        this.articleRepository.saveAll(containArticles);
    }
}

package com.ml.ProductsApi.model.response;

import com.ml.ProductsApi.model.ArticleDTO;

import java.util.List;

public class ArticlesResponseDTO {
    List<ArticleDTO> articles;

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }
}

package com.ml.ProductsApi.dao;

import com.ml.ProductsApi.model.ArticleDTO;

import java.util.List;

public interface IProductDAO {
    List<ArticleDTO> getArticles();
    ArticleDTO getArticleById(Integer id);
    ArticleDTO modify(ArticleDTO modifiedArticle);
}

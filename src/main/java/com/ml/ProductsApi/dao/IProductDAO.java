package com.ml.ProductsApi.dao;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.FilterDTO;

import java.util.List;

public interface IProductDAO {
    List<ArticleDTO> getArticles(FilterDTO filter);
    ArticleDTO getArticleById(Integer id);
    ArticleDTO modify(ArticleDTO modifiedArticle);
}

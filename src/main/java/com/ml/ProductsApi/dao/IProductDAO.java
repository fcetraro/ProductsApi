package com.ml.ProductsApi.dao;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.read.ArticleFromJsonDTO;
import com.ml.ProductsApi.model.request.FilterDTO;

import java.util.List;
import java.util.Map;

public interface IProductDAO {
    List<ArticleDTO> getArticles(Map<String, String> filter);
    ArticleDTO getArticleById(Integer id);
    ArticleDTO modify(ArticleDTO modifiedArticle);
}

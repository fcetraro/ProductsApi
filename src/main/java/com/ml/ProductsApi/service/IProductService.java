package com.ml.ProductsApi.service;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.model.request.QuantityArticleDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;

import java.util.List;

public interface IProductService {
    List<ArticleDTO> getArticles(FilterDTO filters, String sort);
    ArticlesResponseDTO getArticlesById(Integer[] ids);
    ArticlesResponseDTO buyArticles(List<QuantityArticleDTO> articles);
}

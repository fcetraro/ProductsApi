package com.ml.ProductsApi.dao;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;

import java.util.List;
import java.util.function.Predicate;

public interface IProductsDAO {
    List<ArticlesDTO> getArticles(FilterDTO filter);
}

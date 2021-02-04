package com.ml.ProductsApi.service;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;

import java.util.List;

public interface IProductService {
    List<ArticlesDTO> getArticles(FilterDTO filters, String sort);
}

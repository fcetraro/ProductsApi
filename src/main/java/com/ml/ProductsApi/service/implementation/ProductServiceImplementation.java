package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.dao.IProductsDAO;
import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements IProductService {
    @Autowired
    private IProductsDAO products;

    @Override
    public List<ArticlesDTO> getArticles(FilterDTO filters) {
        return products.getArticles(filters);
    }
}

package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.dao.IProductsDAO;
import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.service.IProductService;
import com.ml.ProductsApi.service.ISorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImplementation implements IProductService {
    @Autowired
    private IProductsDAO products;
    private ISorter sorter = SorterFactory.getInstance();
    @Override
    public List<ArticlesDTO> getArticles(FilterDTO filters, String sort) {
        List<ArticlesDTO> articles = products.getArticles(filters);
        if(sort!=null){
            if(SorterFactory.getComparables().containsKey(sort)) {
                sorter.sort(articles,SorterFactory.getComparables().get(sort));
            } else {

            }
        }
        return articles;
    }
}

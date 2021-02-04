package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;
import com.ml.ProductsApi.service.IProductService;
import com.ml.ProductsApi.service.ISorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements IProductService {
    @Autowired
    private IProductDAO products;
    private ISorter sorter = SorterFactory.getInstance();
    @Override
    public List<ArticleDTO> getArticles(FilterDTO filters, String sort) {
        List<ArticleDTO> articles = products.getArticles(filters);
        if(sort!=null){
            if(SorterFactory.getComparables().containsKey(sort)) {
                sorter.sort(articles,SorterFactory.getComparables().get(sort));
            } else {

            }
        }
        return articles;
    }

    @Override
    public ArticlesResponseDTO getArticlesById(Integer[] ids) {
        List<ArticleDTO> lookingForArticles = new ArrayList<>();
        for (Integer id:ids) {
            lookingForArticles.add(products.getArticleById(id));
        }
        ArticlesResponseDTO articles = new ArticlesResponseDTO();
        articles.setArticles(lookingForArticles);
        return articles;
    }
}

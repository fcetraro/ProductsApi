package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.exception.concreteExceptions.NoStockException;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.model.request.QuantityArticleDTO;
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

    @Override
    public ArticlesResponseDTO buyArticles(List<QuantityArticleDTO> articles) {
        List<ArticleDTO> modifiedArticles = new ArrayList<>();
        for (QuantityArticleDTO article:articles) {
            ArticleDTO fullArticle = products.getArticleById(article.getId());
            fullArticle.setQuantity(fullArticle.getQuantity()-article.getQuantityBuyed());
            if(fullArticle.getQuantity()<0){
                throw new NoStockException("El producto " + fullArticle.getName() + " se quedo sin stock.",
                        new Exception());
            }
            modifiedArticles.add(products.modify(fullArticle));
        }
        ArticlesResponseDTO response = new ArticlesResponseDTO();
        response.setArticles(modifiedArticles);
        return response;
    }
}

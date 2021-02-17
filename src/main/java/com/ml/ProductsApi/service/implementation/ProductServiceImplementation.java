package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.exception.concreteExceptions.NoStockException;
import com.ml.ProductsApi.filters.ArticlePredicate;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.QuantityArticleDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;
import com.ml.ProductsApi.service.IProductService;
import com.ml.ProductsApi.service.ISorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @author ${fcetraro}
 */

@Service
public class ProductServiceImplementation implements IProductService {
    @Autowired
    private IProductDAO products;
    private ISorter sorter = SorterFactory.getInstance();
    @Autowired
    public ProductServiceImplementation(IProductDAO products) {
        this.products = products;
    }

    private List<ArticleDTO> applyFilters(List<ArticleDTO> articles, Map<String, String> filter){
        ArticlePredicate filters = new ArticlePredicate();
        Predicate<ArticleDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return articles.stream().filter(predicate).collect(toList());
    }

    @Override
    public List<ArticleDTO> getArticles(Map<String, String> filters, String sort) {
        List<ArticleDTO> articles = applyFilters(products.getArticles(), filters);
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
            int newQuantity = fullArticle.getQuantity()-article.getQuantityBought();
            if(newQuantity<0){
                throw new NoStockException("El producto " + fullArticle.getName() + " se quedo sin stock.",
                        new Exception());
            } else {
                fullArticle.setQuantity(newQuantity);
                modifiedArticles.add(products.modify(fullArticle));
            }
        }
        ArticlesResponseDTO response = new ArticlesResponseDTO();
        response.setArticles(modifiedArticles);
        return response;
    }
}

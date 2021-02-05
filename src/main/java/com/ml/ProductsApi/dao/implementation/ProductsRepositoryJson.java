package com.ml.ProductsApi.dao.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.exception.concreteExceptions.ProductNotFoundException;
import com.ml.ProductsApi.filters.ArticlePredicate;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.read.ArticleFromJsonDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Repository
public class ProductsRepositoryJson implements IProductDAO {
    private final static String db = "articles.json";
    private static List<ArticleDTO> articles;

    private List<ArticleFromJsonDTO> loadArticlesFromFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:"+db);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ArticleFromJsonDTO>> typeRef = new TypeReference<>(){};
        List<ArticleFromJsonDTO> articles = null;
        try{
            articles = objectMapper.readValue(file, typeRef);
        } catch (IOException e){
            e.printStackTrace();
        }
        return articles;
    }

    private List<ArticleDTO> parseArticlesJsonToDTO(List<ArticleFromJsonDTO> fromJson){
        List<ArticleDTO> articles = new ArrayList<>();
        for (ArticleFromJsonDTO article:fromJson) {
            articles.add(new ArticleDTO(articles.size(), article));
        }
        return articles;
    }
    private void loadInitialArticles(){
        if (articles==null){
            articles=parseArticlesJsonToDTO(loadArticlesFromFile());
        }
    }
    @Override
    public List<ArticleDTO> getArticles(FilterDTO filter) {
        loadInitialArticles();
        ArticlePredicate filters = new ArticlePredicate();
        Predicate<ArticleDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return articles.stream().filter(predicate).collect(toList());
    }

    @Override
    public ArticleDTO getArticleById(Integer id) {
        loadInitialArticles();
        try{
            return articles.get(id);
        } catch (Exception e){
            throw new ProductNotFoundException("No se encontro producto ["+id + "]",e);
        }
    }

    @Override
    public ArticleDTO modify(ArticleDTO modifiedArticle) {
        loadInitialArticles();
        articles.set(modifiedArticle.getId(), modifiedArticle);
        return articles.get(modifiedArticle.getId());
    }
}

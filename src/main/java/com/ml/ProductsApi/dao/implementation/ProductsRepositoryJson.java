package com.ml.ProductsApi.dao.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.ProductsApi.dao.IProductsDAO;
import com.ml.ProductsApi.filters.ArticlePredicate;
import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Repository
public class ProductsRepositoryJson implements IProductsDAO {
    private final static String db = "articles.json";
    private static List<ArticlesDTO> articles;

    private List<ArticlesDTO> loadArticlesFromFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:"+db);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ArticlesDTO>> typeRef = new TypeReference<>(){};
        List<ArticlesDTO> articles = null;
        try{
            articles = objectMapper.readValue(file, typeRef);
        } catch (IOException e){
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<ArticlesDTO> getArticles(FilterDTO filter) {
        if (articles==null){
            articles=loadArticlesFromFile();
        }
        ArticlePredicate filters = new ArticlePredicate();
        Predicate<ArticlesDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return articles.stream().filter(predicate).collect(toList());
    }
}

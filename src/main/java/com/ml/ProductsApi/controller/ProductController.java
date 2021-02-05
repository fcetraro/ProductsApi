package com.ml.ProductsApi.controller;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.BuyedArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.model.request.QuantityArticleDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;
import com.ml.ProductsApi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProductService service;
    @GetMapping("/articles")
    public List<ArticleDTO> getArticles(@RequestParam(required = false) Map<String, String> queryMap,
                                        @RequestParam(required = false) String order){
        return service.getArticles(queryMap, order);
    }
    @GetMapping("/articles/search")
    public ArticlesResponseDTO getArticlesById(@RequestParam Integer id[]) {
        return service.getArticlesById(id);
    }
    @PutMapping("/articles/buy")
    public ArticlesResponseDTO buyArticles(@RequestBody BuyedArticlesDTO articlesBuyed) {
        return service.buyArticles(articlesBuyed.getArticles());
    }
}

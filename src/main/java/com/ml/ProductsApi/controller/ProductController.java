package com.ml.ProductsApi.controller;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;
import com.ml.ProductsApi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProductService service;
    @GetMapping("/articles")
    public List<ArticlesDTO> getArticles(@RequestParam(required = false) String brand
            , @RequestParam(required = false) String category
            , @RequestParam(required = false) String prestige
            , @RequestParam(required = false) String price
            , @RequestParam(required = false) String quantity
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String sendFree
            , @RequestParam(required = false) String order){
        FilterDTO newFilters = new FilterDTO(brand,category,prestige,price,quantity,name,sendFree);
        return service.getArticles(newFilters, order);
    }
}

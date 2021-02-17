package com.ml.ProductsApi.controller;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.BoughtArticlesDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;
import com.ml.ProductsApi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ${fcetraro}
 */

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private IProductService service;
    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }
    // getArticles obtiene todos los articulos del Articles Service aplicando los filtros y el ordenamiento
    // (en caso de ser necesario)
    @GetMapping("/articles")
    public List<ArticleDTO> getArticles(@RequestParam(required = false) Map<String, String> queryMap,
                                        @RequestParam(required = false) String order){
        // queryMap es un Map<String, String> donde el key es el nombre del filtro y el value es el valor
        // sort es un String que tiene un numero del 0 al 3
        return service.getArticles(queryMap, order);
    }
    // Obtiene articulos a partir de una lista de ids
    @GetMapping("/articles/search")
    public ArticlesResponseDTO getArticlesById(@RequestParam Integer id[]) {
        // id es la lista de ids a buscar en el service
        return service.getArticlesById(id);
    }
    // Compra articulos
    @PutMapping("/articles/buy")
    public ArticlesResponseDTO buyArticles(@RequestBody BoughtArticlesDTO articlesBuyed) {
        // Recibe un DTO que contiene una lista de QuntityAricleDTO, que estos contiene el id del producto
        // y la cantidad a comprar
        return service.buyArticles(articlesBuyed.getArticles());
    }
}

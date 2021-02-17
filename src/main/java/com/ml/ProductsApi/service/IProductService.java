package com.ml.ProductsApi.service;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.request.QuantityArticleDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * @author ${fcetraro}
 */

public interface IProductService {
    // getArticles obtiene todos los articulos del ArticlesDAO aplicando los filtros y el ordenamiento
    // (en caso de ser necesario)
    // filters es un Map<String, String> donde el key es el nombre del filtro y el value es el valor
    // sort es un String que tiene un numero del 0 al 3
    List<ArticleDTO> getArticles(Map<String, String> filters, String sort);
    // Obtiene articulos a partir de una lista de ids
    // id es la lista de ids a buscar en el service
    ArticlesResponseDTO getArticlesById(Integer[] ids);
    // Compra articulos
    // Recibe una lista de QuntityAricleDTO, que estos contiene el id del producto y la cantidad a comprar
    ArticlesResponseDTO buyArticles(List<QuantityArticleDTO> articles);
}

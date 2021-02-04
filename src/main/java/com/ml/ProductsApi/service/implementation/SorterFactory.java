package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.service.ISorter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SorterFactory {
    public static ISorter getInstance(){return new HeapSortSorterImplementation();}
    public static Map<String, Comparator<ArticleDTO>> getComparables(){
        Map<String, Comparator<ArticleDTO>> comparable = new HashMap<>();
        Comparator<ArticleDTO> byNameAsc = Comparator.comparing(ArticleDTO::getName);
        comparable.put("0",byNameAsc);
        Comparator<ArticleDTO> byNameDes = (a, b)->b.getName().compareTo(a.getName());
        comparable.put("1",byNameDes);
        Comparator<ArticleDTO> byPriceDes = (a, b)->b.getPrice()- a.getPrice();
        comparable.put("2",byPriceDes);
        Comparator<ArticleDTO> byPriceAsc = Comparator.comparingInt(ArticleDTO::getPrice);
        comparable.put("3",byPriceAsc);
        return comparable;
    }
}

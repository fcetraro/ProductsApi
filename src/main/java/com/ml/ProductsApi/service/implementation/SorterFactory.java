package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.service.ISorter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SorterFactory {
    public static ISorter getInstance(){return new HeapSortSorterImplementation();}
    public static Map<String, Comparator<ArticlesDTO>> getComparables(){
        Map<String, Comparator<ArticlesDTO>> comparable = new HashMap<>();
        Comparator<ArticlesDTO> byNameAsc = Comparator.comparing(ArticlesDTO::getName);
        comparable.put("0",byNameAsc);
        Comparator<ArticlesDTO> byNameDes = (a, b)->b.getName().compareTo(a.getName());
        comparable.put("1",byNameDes);
        Comparator<ArticlesDTO> byPriceDes = (a, b)->b.getPrice()- a.getPrice();
        comparable.put("2",byPriceDes);
        Comparator<ArticlesDTO> byPriceAsc = Comparator.comparingInt(ArticlesDTO::getPrice);
        comparable.put("3",byPriceAsc);
        return comparable;
    }
}

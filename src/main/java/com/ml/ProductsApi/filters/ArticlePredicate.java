package com.ml.ProductsApi.filters;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.model.request.FilterDTO;

import java.util.*;
import java.util.function.Predicate;

import static com.ml.ProductsApi.filters.FMFilter.getFilters;

public class ArticlePredicate {
    public Predicate<ArticlesDTO> getCombinedPredicateFromDTO(FilterDTO filters){
        Map<String, String> filterMap = generateMap(filters);
        return getCombinedPredicate(filterMap);
    }
    private Map<String, String> generateMap(FilterDTO filters){
        Map<String, String> filtersMap = new HashMap<>();
        if(filters==null){
            return filtersMap;
        }
        if(filters.getBrand()!=null) {
            filtersMap.put("brand",filters.getBrand());
        }
        if(filters.getCategory()!=null) {
            filtersMap.put("category",filters.getCategory());
        }
        if(filters.getName()!=null) {
            filtersMap.put("name",filters.getName());
        }
        if(filters.getPrestige()!=null) {
            filtersMap.put("prestige", filters.getPrestige());
        }
        if(filters.getPrice()!=null) {
            filtersMap.put("price",filters.getPrice());
        }
        if(filters.getQuantity()!=null) {
            filtersMap.put("quantity",filters.getQuantity());
        }
        if(filters.getSendFree()!=null) {
            filtersMap.put("sendFree",filters.getSendFree());
        }
        return filtersMap;
    }
    private Predicate<ArticlesDTO> getCombinedPredicate(Map<String, String> mapFilters){
        List<Filter> allFilters = getFilters(mapFilters);
        List<Predicate<ArticlesDTO>> allPredicates = new ArrayList<>();
        for (Filter filter:allFilters) {
            allPredicates.add(filter.getPredicate());
        }
        Predicate<ArticlesDTO> compositePredicate =
                allPredicates.stream()
                        .reduce(w -> true, Predicate::and);
        return compositePredicate;
    }
}

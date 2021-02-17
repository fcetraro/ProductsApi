package com.ml.ProductsApi.filters;

import com.ml.ProductsApi.model.ArticleDTO;

import java.util.*;
import java.util.function.Predicate;

import static com.ml.ProductsApi.filters.FMFilter.getFilters;

public class ArticlePredicate {
    public Predicate<ArticleDTO> getCombinedPredicateFromDTO(Map<String, String> filters){
        if(filters.containsKey("order")){
            filters.remove("order");
        }
        return getCombinedPredicate(filters);
    }
    private Predicate<ArticleDTO> getCombinedPredicate(Map<String, String> mapFilters){
        List<Filter> allFilters = getFilters(mapFilters);
        List<Predicate<ArticleDTO>> allPredicates = new ArrayList<>();
        for (Filter filter:allFilters) {
            allPredicates.add(filter.getPredicate());
        }
        Predicate<ArticleDTO> compositePredicate =
                allPredicates.stream()
                        .reduce(w -> true, Predicate::and);
        return compositePredicate;
    }
}

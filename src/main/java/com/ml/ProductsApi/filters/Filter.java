package com.ml.ProductsApi.filters;

import com.ml.ProductsApi.model.ArticleDTO;

import java.util.function.Predicate;

public abstract class Filter {
    public abstract Predicate<ArticleDTO> getPredicate();
    public abstract String getFilterName();
    public boolean matchFilterName(String filterName){
        return getFilterName().equals(filterName);
    }

    public abstract void setValue(String value);
}

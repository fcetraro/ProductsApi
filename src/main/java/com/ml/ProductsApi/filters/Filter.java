package com.ml.ProductsApi.filters;

import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

public abstract class Filter {
    public abstract Predicate<ArticlesDTO> getPredicate();
    public abstract String getFilterName();
    public boolean matchFilterName(String filterName){
        return getFilterName().equals(filterName);
    }

    public abstract void setValue(String value);
}

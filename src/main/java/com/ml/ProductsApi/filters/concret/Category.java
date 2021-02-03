package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

public class Category extends Filter {

    String categoryName;

    @Override
    public Predicate<ArticlesDTO> getPredicate() {
        String a=categoryName;
        return w -> w.getCategory().equals(categoryName);
    }

    @Override
    public String getFilterName() {
        return "category";
    }

    @Override
    public void setValue(String value) {
        categoryName=value;
    }
}

package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.ArticleDTO;

import java.util.function.Predicate;

public class Category extends Filter {

    String categoryName;

    @Override
    public Predicate<ArticleDTO> getPredicate() {
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

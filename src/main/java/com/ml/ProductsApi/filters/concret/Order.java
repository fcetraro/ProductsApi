package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.ArticleDTO;

import java.util.function.Predicate;

public class Order extends Filter {

    String order;

    @Override
    public Predicate<ArticleDTO> getPredicate() {
        return w -> true;
    }

    @Override
    public String getFilterName() {
        return "order";
    }
    @Override
    public void setValue(String value) {
        order = value;
    }
}

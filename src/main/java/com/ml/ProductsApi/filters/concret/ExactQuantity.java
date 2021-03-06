package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.ArticleDTO;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class ExactQuantity extends Filter {

    int quantity;

    @Override
    public Predicate<ArticleDTO> getPredicate() {
        return w -> w.getQuantity()==quantity;
    }

    @Override
    public String getFilterName() {
        return "quantity";
    }
    @Override
    public void setValue(String value) {
        quantity = parseInt(value);
    }

}

package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class ExactPrice extends Filter {

    int price;

    @Override
    public Predicate<ArticlesDTO> getPredicate() {
        return w -> w.getPrice()==price;
    }

    @Override
    public String getFilterName() {
        return "price";
    }
    @Override
    public void setValue(String value) {
        price = parseInt(value);
    }
}

package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class Name extends Filter {

    String name;

    @Override
    public Predicate<ArticlesDTO> getPredicate() {
        return w -> w.getName().equals(name);
    }

    @Override
    public String getFilterName() {
        return "name";
    }
    @Override
    public void setValue(String value) {
        name = value;
    }
}

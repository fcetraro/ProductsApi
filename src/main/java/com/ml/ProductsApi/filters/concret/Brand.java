package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

public class Brand extends Filter {

    String brand;

    @Override
    public Predicate<ArticlesDTO> getPredicate() {
        return w -> w.getBrand().equals(brand);
    }

    @Override
    public String getFilterName() {
        return "brand";
    }

    @Override
    public void setValue(String value) {
        brand=value;
    }
}

package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.ArticleDTO;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class SendFree extends Filter {

    @Override
    public Predicate<ArticleDTO> getPredicate() {
        return w -> w.isFreeShipping();
    }

    @Override
    public String getFilterName() {
        return "sendFree";
    }
    @Override
    public void setValue(String value) {}
}

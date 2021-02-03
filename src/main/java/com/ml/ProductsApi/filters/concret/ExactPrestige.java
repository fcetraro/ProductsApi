package com.ml.ProductsApi.filters.concret;

import com.ml.ProductsApi.filters.Filter;
import com.ml.ProductsApi.model.read.ArticlesDTO;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class ExactPrestige extends Filter {

    int prestige;

    @Override
    public Predicate<ArticlesDTO> getPredicate() {
        return w -> w.getPrestige()==prestige;
    }

    @Override
    public String getFilterName() {
        return "prestige";
    }

    @Override
    public void setValue(String value) {
        prestige = parseInt(value);
    }

}

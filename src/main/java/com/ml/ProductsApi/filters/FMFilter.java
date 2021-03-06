package com.ml.ProductsApi.filters;

import com.ml.ProductsApi.exception.concreteExceptions.FilterNotFoundException;
import com.ml.ProductsApi.exception.concreteExceptions.WrongCastFilterException;
import com.ml.ProductsApi.filters.concret.*;

import java.util.*;

public class FMFilter {
    public static List<Filter> getFilters(Map<String, String> mapFilters){
        List<Filter> filters = new ArrayList<>();
        Iterator it = mapFilters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Filter concretFilter = getConcretFilter((String)pair.getKey(), (String)pair.getValue());
            if(concretFilter!=null){
                filters.add(concretFilter);
            }
            it.remove();
        }
        return filters;
    }

    private static Filter getConcretFilter(String filter, String value) {
        for (Filter concretFilter:getAllFilters()) {
            if(concretFilter.matchFilterName(filter)) {
                try {
                    concretFilter.setValue(value);
                    return concretFilter;
                } catch (Exception e){
                    String message = "Tipo ingresado para el filtro "+concretFilter.getFilterName() +" no es valido.";
                    throw new WrongCastFilterException(message, e);
                }
            }
        }
        throw new FilterNotFoundException("Filtro [" + filter + "] no reconocido.", new Exception());
    }

    private static List<Filter> getAllFilters(){
        List<Filter> allFilters = new ArrayList<>();
        allFilters.add(new Brand());
        allFilters.add(new Category());
        allFilters.add(new ExactPrice());
        allFilters.add(new ExactPrestige());
        allFilters.add(new ExactQuantity());
        allFilters.add(new Name());
        allFilters.add(new SendFree());
        return allFilters;
    }
}

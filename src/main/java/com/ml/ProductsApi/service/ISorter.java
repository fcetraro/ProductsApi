package com.ml.ProductsApi.service;

import java.util.Comparator;
import java.util.List;

/**
 * @author ${fcetraro}
 */

public interface ISorter<T> {
        // Ordena la lista
        // Recibe la lista generica y el comparador para usar como criterio para ordenar la lista
        void sort(List<T> list, Comparator<T> c);
}

package com.ml.ProductsApi.service;

import java.util.Comparator;
import java.util.List;

public interface ISorter<T> {
        void sort(List<T> list, Comparator<T> c);
}

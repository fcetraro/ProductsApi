package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.service.ISorter;

import java.util.Comparator;
import java.util.List;

/**
 * @author ${fcetraro}
 */

public class HeapSortSorterImplementation implements ISorter<ArticleDTO> {
    void heapify(List<ArticleDTO> arr, int n, int i, Comparator c)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && c.compare(arr.get(l),arr.get(largest))>=0)
            largest = l;
        if (r < n && c.compare(arr.get(r),arr.get(largest))>=0)
            largest = r;
        if (largest != i) {
            ArticleDTO swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, n, largest, c);
        }
    }

    @Override
    public void sort(List<ArticleDTO> arr, Comparator<ArticleDTO> c) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, c);
        for (int i = n - 1; i > 0; i--) {
            ArticleDTO temp = arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i,temp);
            heapify(arr, i, 0, c);
        }
    }
}

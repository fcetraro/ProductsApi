package com.ml.ProductsApi.service.implementation;

import com.ml.ProductsApi.model.read.ArticlesDTO;
import com.ml.ProductsApi.service.ISorter;

import java.util.Comparator;
import java.util.List;

public class HeapSortSorterImplementation implements ISorter<ArticlesDTO> {
    void heapify(List<ArticlesDTO> arr, int n, int i, Comparator c)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && c.compare(arr.get(l),arr.get(largest))>=0)
            largest = l;
        if (r < n && c.compare(arr.get(r),arr.get(largest))>=0)
            largest = r;
        if (largest != i) {
            ArticlesDTO swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, n, largest, c);
        }
    }

    @Override
    public void sort(List<ArticlesDTO> arr, Comparator<ArticlesDTO> c) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, c);
        for (int i = n - 1; i > 0; i--) {
            ArticlesDTO temp = arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i,temp);
            heapify(arr, i, 0, c);
        }
    }
}

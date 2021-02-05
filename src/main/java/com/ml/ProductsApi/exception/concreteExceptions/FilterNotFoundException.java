package com.ml.ProductsApi.exception.concreteExceptions;

public class FilterNotFoundException extends RuntimeException {
    public FilterNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

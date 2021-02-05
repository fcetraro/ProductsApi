package com.ml.ProductsApi.exception.concreteExceptions;

public class NoStockException extends RuntimeException {
    public NoStockException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

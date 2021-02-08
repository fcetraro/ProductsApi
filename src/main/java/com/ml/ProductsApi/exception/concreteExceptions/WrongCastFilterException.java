package com.ml.ProductsApi.exception.concreteExceptions;

public class WrongCastFilterException extends RuntimeException {
    public WrongCastFilterException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

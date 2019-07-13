package com.stackroute.productsearchservice.exception;

public class ProductAlreadyExistsException extends Exception {
    private String message;
    public ProductAlreadyExistsException(){}

    public ProductAlreadyExistsException(String message) {
        super(message);
        this.message=message;
    }
}


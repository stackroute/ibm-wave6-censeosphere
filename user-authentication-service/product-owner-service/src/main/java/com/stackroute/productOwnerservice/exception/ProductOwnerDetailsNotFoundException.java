package com.stackroute.productOwnerservice.exception;

public class ProductOwnerDetailsNotFoundException extends Exception {
    private String message;
    public ProductOwnerDetailsNotFoundException(){}

    public ProductOwnerDetailsNotFoundException(String message) {
        super(message);
        this.message=message;
    }

}


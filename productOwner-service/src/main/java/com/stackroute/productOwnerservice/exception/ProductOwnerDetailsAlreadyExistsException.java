package com.stackroute.productOwnerservice.exception;

public class ProductOwnerDetailsAlreadyExistsException  extends Exception {
    private String message;
    public ProductOwnerDetailsAlreadyExistsException(){}

    public ProductOwnerDetailsAlreadyExistsException(String message) {
        super(message);
        this.message=message;
    }
}

package com.stackroute.searchservice.exception;

public class SubcategoryNotFoundException extends Exception{
    private  String message;

    public SubcategoryNotFoundException() {
    }

    public SubcategoryNotFoundException(String message) {
        this.message = message;
    }
}

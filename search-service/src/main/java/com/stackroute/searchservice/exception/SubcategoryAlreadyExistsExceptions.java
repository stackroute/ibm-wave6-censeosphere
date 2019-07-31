package com.stackroute.searchservice.exception;

public class SubcategoryAlreadyExistsExceptions extends Exception{
     String message;
    public SubcategoryAlreadyExistsExceptions(){}

    public SubcategoryAlreadyExistsExceptions(String message) {
        super(message);
        this.message=message;
    }
}

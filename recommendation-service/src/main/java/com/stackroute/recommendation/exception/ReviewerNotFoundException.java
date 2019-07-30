package com.stackroute.recommendation.exception;

public class ReviewerNotFoundException extends Exception{
    private String message;

    public ReviewerNotFoundException() {
    }

    public ReviewerNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}

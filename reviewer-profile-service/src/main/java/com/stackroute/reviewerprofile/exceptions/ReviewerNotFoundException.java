package com.stackroute.reviewerprofile.exceptions;

public class ReviewerNotFoundException extends Exception {
    private String message;
    public ReviewerNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }

    public ReviewerNotFoundException() {
    }
}

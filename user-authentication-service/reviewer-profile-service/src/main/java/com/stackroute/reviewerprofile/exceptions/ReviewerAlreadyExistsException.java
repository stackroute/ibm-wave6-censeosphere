package com.stackroute.reviewerprofile.exceptions;

public class ReviewerAlreadyExistsException extends Exception{
    private String message;
    public ReviewerAlreadyExistsException(String message)
    {
        super(message);
        this.message=message;
    }

}

package com.stackroute.recommendation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException extends RuntimeException {
    @ExceptionHandler(value = ReviewerNotFoundException.class)
    public ResponseEntity<String> exception(ReviewerNotFoundException exception)
    {
        return  new ResponseEntity<>("Reviewer Not Found ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception (Exception exception)
    {
        return new ResponseEntity<>( "List is empty",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<String> exceptionP(ProductNotFoundException exception)
    {
        return  new ResponseEntity<>("Product Not Found ", HttpStatus.NOT_FOUND);
    }

}

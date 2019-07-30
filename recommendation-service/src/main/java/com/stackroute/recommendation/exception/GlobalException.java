package com.stackroute.recommendation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException extends RuntimeException {
    @ExceptionHandler(value = ReviewerNotFoundException.class)
    public ResponseEntity<?> exception(ReviewerNotFoundException exception)
    {
        return  new ResponseEntity<String>("Reviewer Not Found ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception (Exception exception)
    {
        return new ResponseEntity( "List is empty",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> exceptionP(ProductNotFoundException exception)
    {
        return  new ResponseEntity<String>("Product Not Found ", HttpStatus.NOT_FOUND);
    }

}

package com.stackroute.reviewerprofile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewerExceptionController {

    @ExceptionHandler(value= ReviewerAlreadyExistsException.class)
    public ResponseEntity<String> ReviewerAlreadyExist(ReviewerAlreadyExistsException exception)
    {
        return new ResponseEntity<String>("Reviewer Already Exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= ReviewerNotFoundException.class)
    public ResponseEntity<String> ReviewerNotFound(ReviewerNotFoundException exception1)
    {
        return new ResponseEntity<String>("Reviewer Not Found", HttpStatus.NOT_FOUND);
    }

}

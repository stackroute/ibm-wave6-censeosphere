package com.stackroute.productsearchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private static final long serialVersionUID = 1L;
    @ExceptionHandler(value = ProductNotFoundException.class)

    public ResponseEntity<Object> exception(ProductNotFoundException exception) {

        return new ResponseEntity<>("Details Not Found!", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ProductAlreadyExistsException.class)

    public ResponseEntity<Object> exception(ProductAlreadyExistsException exception) {

        return new ResponseEntity<>("Details Already Exists!", HttpStatus.CONFLICT);
    }
}
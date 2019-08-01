package com.stackroute.productOwnerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    @ExceptionHandler(value = ProductOwnerDetailsNotFoundException.class)

    public ResponseEntity<Object> exception(ProductOwnerDetailsNotFoundException exception) {

        return new ResponseEntity<>("Details Not Found!", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ProductOwnerDetailsAlreadyExistsException.class)

    public ResponseEntity<Object> exception(ProductOwnerDetailsAlreadyExistsException exception) {

        return new ResponseEntity<>("Details Already Exists!", HttpStatus.CONFLICT);
    }

}

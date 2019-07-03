package com.stackroute.userloginservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<?> handleUserAlraedyExistsException(UserAlreadyExistsException e) {
            return new ResponseEntity<String>("User Already Exists !", HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<?> handleUserNameNotFoundException(UserNotFoundException e){
           return new ResponseEntity<String>("User Not Found !", HttpStatus.NOT_FOUND);
       }


        @ExceptionHandler(PasswordNotMatchException.class)
        public ResponseEntity<?> handlePasswordNotMatchException(PasswordNotMatchException e){
            return new ResponseEntity<String>("Password Doesn't Match !", HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(UserNameOrPasswordEmptyException.class)
        public ResponseEntity<?> handleUserNameOrPasswordEmptyException(UserNameOrPasswordEmptyException e){
            return new ResponseEntity<String>("User Name or Password is Empty !", HttpStatus.NOT_FOUND);
        }

}
package com.dq.work4.handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(){
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

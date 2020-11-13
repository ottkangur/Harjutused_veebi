package com.example.proov.erandid;

import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)   //konkreetne kliendipoolne viga, mille sisu on teada ja mida oleks hea talle tagastada
    public ResponseEntity<Object> handleApplicationException(ApplicationException e){
        System.out.println("Status code: 400");  //kliendi viga ei ole mõtet logisse panna
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)      //mingi üldine viga, mille sisu ei ole teada
    public ResponseEntity<Object> handleError (Exception e){
        e.printStackTrace();        //tagastab arendajale teate logisse; server errori puhul oluline
        return new ResponseEntity<>(new ErrorResponse("Server error"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

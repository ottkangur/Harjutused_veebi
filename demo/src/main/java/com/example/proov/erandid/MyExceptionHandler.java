package com.example.proov.erandid;

import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    //@ExceptionHandler seob selle osa ApplicationException klassiga
    @ExceptionHandler(ApplicationException.class)   //konkreetne kliendipoolne viga, mille sisu on teada ja mida oleks hea talle tagastada
    public ResponseEntity<Object> handleApplicationException(ApplicationException e){
        System.out.println("Status code: 400");  //kliendi viga ei ole mõtet spetsiifiliselt logisse panna; 400 on piisav
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),
//                new HttpHeaders(),        //valikuline
                HttpStatus.BAD_REQUEST);    //see rida määrab, et kliendile tagastatakse 400
    }

    @ExceptionHandler(Exception.class)      //mingi serveri viga, mille sisu ei ole teada
    public ResponseEntity<Object> handleError (Exception e){
        e.printStackTrace();        //tagastab arendajale teate logisse; server errori puhul oluline
        return new ResponseEntity<>(new ErrorResponse("Server error"),  //see rida pöördub ErrorResponse klassi poole
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);  //see rida määrab, et kliendile tagastatakse 500
    }
}

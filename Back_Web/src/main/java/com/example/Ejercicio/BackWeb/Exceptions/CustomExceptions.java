package com.example.Ejercicio.BackWeb.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleUnprocesableException(UnprocesableException u){
        CustomError customError=new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), u.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY.toString(), new Date() );
        return new ResponseEntity<CustomError>(customError,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

package com.example.Ejercicio.BackWeb.Exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleUnprocesableException(UnprocesableException u){
        CustomError customError=new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), u.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY.toString(), new Date() );
        return new ResponseEntity<CustomError>(customError,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FeignException.class)
    public final ResponseEntity<Void> handleFeignException(FeignException feignException){
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoPlazasException.class)
    public final ResponseEntity<CustomError> handleNoPlazasException(NoPlazasException noPlazasException){
        CustomError customError= new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(), noPlazasException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), new Date());
        return new ResponseEntity<CustomError>(customError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.example.Ejercicio.BackWeb.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}

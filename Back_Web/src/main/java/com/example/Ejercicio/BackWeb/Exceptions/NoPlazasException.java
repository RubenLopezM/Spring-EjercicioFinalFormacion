package com.example.Ejercicio.BackWeb.Exceptions;

public class NoPlazasException extends RuntimeException{

    public NoPlazasException(String mensaje){
        super(mensaje);
    }
}

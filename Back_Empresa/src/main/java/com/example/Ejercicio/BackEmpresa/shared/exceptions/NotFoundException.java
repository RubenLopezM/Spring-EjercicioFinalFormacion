package com.example.Ejercicio.BackEmpresa.shared.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}

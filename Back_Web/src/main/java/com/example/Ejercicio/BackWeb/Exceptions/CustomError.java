package com.example.Ejercicio.BackWeb.Exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class CustomError {

    private int httpCode;
    private String mensaje;
    private String tipo;
    private Date timestamp;

    public CustomError(int httpCode, String mensaje,String tipo,Date timestamp){
        super();

        this.httpCode=httpCode;
        this.mensaje=mensaje;
        this.tipo=tipo;
        this.timestamp=timestamp;

    }
}

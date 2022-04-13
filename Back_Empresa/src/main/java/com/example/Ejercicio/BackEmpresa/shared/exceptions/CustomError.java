package com.example.Ejercicio.BackEmpresa.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class CustomError {
    private int httpCode;
    private String mensaje;
    private String tipo;
    private Date timestamp;
}

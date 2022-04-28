package com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CorreoInputDTO {
    private String ciudad_destino;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;
}

package com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaInputDTO {
    private String ciudadDestino;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;
}

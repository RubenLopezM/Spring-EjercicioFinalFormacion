package com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO;

import lombok.Data;

import java.util.Date;

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

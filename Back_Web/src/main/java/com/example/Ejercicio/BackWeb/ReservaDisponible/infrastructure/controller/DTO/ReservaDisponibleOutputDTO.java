package com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReservaDisponibleOutputDTO {
    private String ciudadDestino;
    private Date fechaSalida;
    private float horaSalida;
    private Integer numeroPlazas;
}

package com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaInputDTO {
    private String ciudad;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private Date fecha;
    private Float hora;
}

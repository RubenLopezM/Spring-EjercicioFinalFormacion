package com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class CorreoOutputDTO {
    private String ciudad_destino;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;
}

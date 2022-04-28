package com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO;

import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaInputDTO {
    private String ciudad;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private Date fecha;
    private Float hora;

    public ReservaInputDTO(Reserva reserva){
        this.ciudad= reserva.getCiudad();
        this.nombre=reserva.getNombre();
        this.apellidos=reserva.getApellidos();
        this.telefono=reserva.getTelefono();
        this.email=reserva.getEmail();
        this.fecha=reserva.getFecha();
        this.hora=reserva.getHora();
    }
}

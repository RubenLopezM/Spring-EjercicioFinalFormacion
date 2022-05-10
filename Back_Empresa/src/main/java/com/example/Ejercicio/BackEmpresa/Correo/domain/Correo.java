package com.example.Ejercicio.BackEmpresa.Correo.domain;

import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class Correo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String id_email;
    @Column(nullable = false)
    private String ciudad_destino;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private Date fechaReserva;
    @Column(nullable = false)
    private Float horaReserva;

}

package com.example.Ejercicio.BackWeb.Reserva.domain;

import com.example.Ejercicio.BackWeb.Autobus.domain.Autobus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reservas")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String id_reserva;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private Float hora;
    @ManyToOne(fetch = FetchType.LAZY)
    private Autobus autobus;

}

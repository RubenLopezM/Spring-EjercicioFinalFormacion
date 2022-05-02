package com.example.Ejercicio.BackWeb.Reserva.domain;

import com.example.Ejercicio.BackWeb.Autobus.domain.Autobus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    private Float hora;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad",referencedColumnName = "ciudad",insertable = false,updatable = false)
    @JoinColumn(name = "fecha",referencedColumnName = "fecha",insertable = false,updatable = false)
    @JoinColumn(name = "hora",referencedColumnName = "hora",insertable = false,updatable = false)
    private Autobus autobus;

}

package com.example.Ejercicio.BackEmpresa.Reserva.domain;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Correo.domain.Correo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID identificador;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false, length = 9)
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

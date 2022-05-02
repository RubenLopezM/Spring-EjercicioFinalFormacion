package com.example.Ejercicio.BackWeb.Autobus.domain;

import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponibleID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Autobus {


    @EmbeddedId
    private ReservaDisponibleID reservaDisponibleID;
    private int capacidad;
    @OneToOne
    @JoinColumn(name="ciudad", referencedColumnName="ciudad")
    @JoinColumn(name="fecha", referencedColumnName="fecha")
    @JoinColumn(name="hora", referencedColumnName="hora")
    @MapsId
    private ReservaDisponible reservasdisponibles;
    @OneToMany(mappedBy = "autobus")
    private List<Reserva> reservas=new ArrayList<Reserva>();


}

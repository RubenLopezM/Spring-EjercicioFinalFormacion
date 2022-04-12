package com.example.Ejercicio.BackWeb.ReservaDisponible.domain;

import com.example.Ejercicio.BackWeb.Autobus.domain.Autobus;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class ReservaDisponible {

    @EmbeddedId
    private ReservaDisponibleID id;
    private int plazasdisponibles;
    @OneToOne(mappedBy = "reservasdisponibles",cascade = CascadeType.ALL)
    private Autobus autobus;

}

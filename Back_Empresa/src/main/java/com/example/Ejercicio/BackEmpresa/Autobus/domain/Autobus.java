package com.example.Ejercicio.BackEmpresa.Autobus.domain;

import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Autobus {
    @EmbeddedId
    private AutobusID id;
    private int totalPlazas;
    private int plazasdisponibles;
    @OneToMany(mappedBy = "autobus",cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}

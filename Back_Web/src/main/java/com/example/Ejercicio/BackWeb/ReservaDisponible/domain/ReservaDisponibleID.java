package com.example.Ejercicio.BackWeb.ReservaDisponible.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDisponibleID implements Serializable {
    private String ciudad;
    private Date fecha;
    private float hora;
}

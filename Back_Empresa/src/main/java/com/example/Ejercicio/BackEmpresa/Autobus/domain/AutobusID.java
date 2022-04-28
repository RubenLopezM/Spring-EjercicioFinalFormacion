package com.example.Ejercicio.BackEmpresa.Autobus.domain;

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
public class AutobusID implements Serializable {
    private String ciudad;
    private Date fecha;
    private float hora;
}

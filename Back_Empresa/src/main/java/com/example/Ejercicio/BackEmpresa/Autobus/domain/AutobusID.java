package com.example.Ejercicio.BackEmpresa.Autobus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutobusID implements Serializable {
    private String ciudad;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private float hora;
}

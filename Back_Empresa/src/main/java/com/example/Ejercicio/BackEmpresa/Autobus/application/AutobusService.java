package com.example.Ejercicio.BackEmpresa.Autobus.application;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;

import java.text.ParseException;
import java.util.Date;

public interface AutobusService {
    public Autobus checkAutobus(ReservaInputDTO reservaInputDTO);
    public Autobus findAutobus(String ciudad, Date fecha, Float hora);
    public String findPlazaslibres(String ciudad, Date fecha, Float hora);
}

package com.example.Ejercicio.BackEmpresa.Autobus.application;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;

public interface AutobusService {
    public Autobus checkAutobus(ReservaInputDTO reservaInputDTO);
}

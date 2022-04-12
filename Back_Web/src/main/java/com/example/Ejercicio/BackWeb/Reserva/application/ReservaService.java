package com.example.Ejercicio.BackWeb.Reserva.application;

import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;

public interface ReservaService {
    public ReservaOutputDTO addReserva(ReservaInputDTO reservaInputDTO);
}

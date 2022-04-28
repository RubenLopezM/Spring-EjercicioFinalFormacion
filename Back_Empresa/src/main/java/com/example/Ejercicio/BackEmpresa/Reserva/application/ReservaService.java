package com.example.Ejercicio.BackEmpresa.Reserva.application;

import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;

public interface ReservaService {
    public void escucharReserva(ReservaInputDTO reservaInputDTO);
    public ReservaOutputDTO hacerReserva(ReservaInputDTO reservaInputDTO);
    public void actualizarReservas();
}

package com.example.Ejercicio.BackWeb.Reserva.application;

import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO.ReservaDisponibleOutputDTO;

import java.util.HashMap;
import java.util.List;

public interface ReservaService {
    public ReservaOutputDTO addReserva(ReservaInputDTO reservaInputDTO);
    public List<ReservaOutputDTO> searchReservas(HashMap<String, Object> conditions);
    public void listenReserva (ReservaInputDTO reservaInputDTO);
}

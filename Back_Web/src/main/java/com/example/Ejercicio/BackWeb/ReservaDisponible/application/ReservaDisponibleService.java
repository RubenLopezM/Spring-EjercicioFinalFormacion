package com.example.Ejercicio.BackWeb.ReservaDisponible.application;

import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO.ReservaDisponibleOutputDTO;

import java.util.HashMap;
import java.util.List;

public interface ReservaDisponibleService {
    public ReservaDisponible createReservaDisp(ReservaInputDTO reservaInputDTO);
    public List<ReservaDisponibleOutputDTO> searchPlazas(HashMap<String, Object> conditions);
}

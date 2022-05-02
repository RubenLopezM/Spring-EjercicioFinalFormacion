package com.example.Ejercicio.BackEmpresa.Reserva.application;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ReservaService {
    public void escucharReserva(ReservaInputDTO reservaInputDTO);
    public ReservaOutputDTO hacerReserva(ReservaInputDTO reservaInputDTO) ;
    public void actualizarReservas();
    public List<ReservaOutputDTO> findReservasinAutobus(String ciudad, Date fecha, Float hora);
}

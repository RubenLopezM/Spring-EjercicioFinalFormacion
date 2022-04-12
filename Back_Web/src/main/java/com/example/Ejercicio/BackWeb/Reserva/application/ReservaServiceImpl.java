package com.example.Ejercicio.BackWeb.Reserva.application;

import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository.ReservaRepo;
import com.example.Ejercicio.BackWeb.ReservaDisponible.application.ReservaDisponibleService;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepo reservaRepo;

    @Autowired
    ReservaDisponibleService reservaDisponibleService;




    @Override
    public ReservaOutputDTO addReserva(ReservaInputDTO reservaInputDTO) {
        Reserva reserva= convertToEntity(reservaInputDTO);
        ReservaDisponible rd= reservaDisponibleService.createReservaDisp(reservaInputDTO);
        reserva.setAutobus(rd.getAutobus());
        reservaRepo.save(reserva);
        return convertToDTO(reserva);
    }

    private Reserva convertToEntity(ReservaInputDTO reservaInputDTO){
        Reserva reserva= new Reserva();
        reserva.setCiudad_destino(reservaInputDTO.getCiudadDestino());
        reserva.setNombre(reservaInputDTO.getNombre());
        reserva.setApellidos(reservaInputDTO.getApellidos());
        reserva.setEmail(reservaInputDTO.getEmail());
        reserva.setTelefono(reservaInputDTO.getTelefono());
        reserva.setFechaReserva(reservaInputDTO.getFechaReserva());
        reserva.setHoraReserva(reservaInputDTO.getHoraReserva());
        return reserva;
    }

    private ReservaOutputDTO convertToDTO(Reserva reserva){
        ReservaOutputDTO reservaOutputDTO= new ReservaOutputDTO();
        reservaOutputDTO.setCiudadDestino(reserva.getCiudad_destino());
        reservaOutputDTO.setNombre(reserva.getNombre());
        reservaOutputDTO.setApellidos(reserva.getApellidos());
        reservaOutputDTO.setTelefono(reserva.getTelefono());
        reservaOutputDTO.setEmail(reserva.getEmail());
        reservaOutputDTO.setFechaReserva(reserva.getFechaReserva());
        reservaOutputDTO.setHoraReserva(reserva.getHoraReserva());
        return reservaOutputDTO;
    }
}

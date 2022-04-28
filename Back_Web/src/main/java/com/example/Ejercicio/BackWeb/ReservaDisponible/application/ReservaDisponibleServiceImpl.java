package com.example.Ejercicio.BackWeb.ReservaDisponible.application;

import com.example.Ejercicio.BackWeb.Autobus.domain.Autobus;
import com.example.Ejercicio.BackWeb.Exceptions.NoPlazasException;
import com.example.Ejercicio.BackWeb.Exceptions.UnprocesableException;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponibleID;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO.ReservaDisponibleOutputDTO;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.repository.ReservaDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaDisponibleServiceImpl implements ReservaDisponibleService {

    @Autowired
    ReservaDisponibleRepository reservaDisponibleRepo;



    @Override
    public ReservaDisponible createReservaDisp(ReservaInputDTO reservaInputDTO) {

        ReservaDisponible rd = convertToReservaDisp(reservaInputDTO);

        checkReservasDisponibles(rd);
        return rd;
    }

    @Override
    public List<ReservaDisponibleOutputDTO> searchPlazas(HashMap<String, Object> conditions) {
        List<ReservaDisponible> reservaDisponibles=reservaDisponibleRepo.getData(conditions);
        List<ReservaDisponibleOutputDTO> reservaDisponiblesDTO= reservaDisponibles.stream()
                .map(reservaDisponible -> convertToDTO(reservaDisponible))
                .collect(Collectors.toList());
        return reservaDisponiblesDTO;
    }

    private ReservaDisponible convertToReservaDisp(ReservaInputDTO reservaInputDTO) {
        ReservaDisponible rd = new ReservaDisponible();
        rd.setId(new ReservaDisponibleID(reservaInputDTO.getCiudad(), reservaInputDTO.getFecha(), reservaInputDTO.getHora()));
        rd.setPlazasdisponibles(40);
        return rd;

    }

    private ReservaDisponibleOutputDTO convertToDTO(ReservaDisponible rs) {
        ReservaDisponibleOutputDTO rdDTO = new ReservaDisponibleOutputDTO();
        rdDTO.setCiudadDestino(rs.getId().getCiudad());
        rdDTO.setFechaSalida(rs.getId().getFecha());
        rdDTO.setHoraSalida(rs.getId().getHora());
        rdDTO.setNumeroPlazas(rs.getPlazasdisponibles());
        return rdDTO;
    }

    private void checkReservasDisponibles(ReservaDisponible rs) {

        String ciudad = rs.getId().getCiudad();
        Date fecha = rs.getId().getFecha();
        float hora = rs.getId().getHora();
        int maximaCapacidad=40;
        Optional<ReservaDisponible> rd = reservaDisponibleRepo.findById(new ReservaDisponibleID(ciudad, fecha, hora));
       if (!rd.isPresent()){
           Autobus autobus = new Autobus(rs.getId(), maximaCapacidad, rs, new ArrayList<>());
           rs.setAutobus(autobus);
           rs.setPlazasdisponibles(maximaCapacidad -1);
           reservaDisponibleRepo.save(rs);
       } else {

           int plazas= rd.get().getPlazasdisponibles();
           if (plazas==0){
               throw new NoPlazasException("No quedan plazas en el autobus");
           }
           Autobus autobus = rd.get().getAutobus();
           rs.setAutobus(autobus);
           rd.get().setPlazasdisponibles(plazas-1);
           reservaDisponibleRepo.save(rd.get());
       }

    }

}


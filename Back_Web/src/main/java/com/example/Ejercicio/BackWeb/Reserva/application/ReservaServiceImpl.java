package com.example.Ejercicio.BackWeb.Reserva.application;

import com.example.Ejercicio.BackWeb.Exceptions.UnprocesableException;
import com.example.Ejercicio.BackWeb.Kafka.MessageProducer;
import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository.ReservaRepository;
import com.example.Ejercicio.BackWeb.ReservaDisponible.application.ReservaDisponibleService;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepository reservaRepo;

    @Autowired
    ReservaDisponibleService reservaDisponibleService;

    @Autowired
    MessageProducer messageProducer;





    @Override
    public ReservaOutputDTO addReserva(ReservaInputDTO reservaInputDTO) {
        this.checkDestinos(reservaInputDTO.getCiudad());
        this.checkHorarios(reservaInputDTO.getHora());
        if (reservaRepo.findByCiudadAndFechaAndHoraAndEmailAndNombre(reservaInputDTO.getCiudad(),reservaInputDTO.getFecha(), reservaInputDTO.getHora(), reservaInputDTO.getEmail(),reservaInputDTO.getNombre())!=null){
            throw new UnprocesableException("Ya existe una reserva con estos datos");
        }
        Date fecha=reservaInputDTO.getFecha();
        if (fecha.before(new Date(System.currentTimeMillis()))){
            throw new UnprocesableException("La fecha no puede ser anterior a la actual");
        };
        Reserva reserva= convertToEntity(reservaInputDTO);
        ReservaDisponible rd= reservaDisponibleService.createReservaDisp(reservaInputDTO);
        reserva.setAutobus(rd.getAutobus());
        messageProducer.sendMessage(reservaInputDTO);
        reservaRepo.save(reserva);
        return convertToDTO(reserva);
    }

    @Override
    public List<ReservaOutputDTO> searchReservas(HashMap<String, Object> conditions) {
        List<Reserva> reservas=reservaRepo.getReservas(conditions);
        List<ReservaOutputDTO> reservaOutputDTOS= reservas.stream()
                .map((reserva)-> convertToDTO(reserva))
                .collect(Collectors.toList());
        return  reservaOutputDTOS;
    }

    @Override
    public void listenReserva(ReservaInputDTO reservaInputDTO) {
        if (reservaRepo.findByCiudadAndFechaAndHoraAndEmailAndNombre(reservaInputDTO.getCiudad(),reservaInputDTO.getFecha(), reservaInputDTO.getHora(), reservaInputDTO.getEmail(),reservaInputDTO.getNombre())!=null){
            return;
        }

        Reserva reserva= convertToEntity(reservaInputDTO);
        ReservaDisponible rd= reservaDisponibleService.createReservaDisp(reservaInputDTO);
        reserva.setAutobus(rd.getAutobus());
        reservaRepo.save(reserva);

    }

    private Reserva convertToEntity(ReservaInputDTO reservaInputDTO){
        Reserva reserva= new Reserva();
        reserva.setCiudad(reservaInputDTO.getCiudad());
        reserva.setNombre(reservaInputDTO.getNombre());
        reserva.setApellidos(reservaInputDTO.getApellidos());
        reserva.setEmail(reservaInputDTO.getEmail());
        reserva.setTelefono(reservaInputDTO.getTelefono());
        reserva.setFecha(reservaInputDTO.getFecha());
        reserva.setHora(reservaInputDTO.getHora());
        return reserva;
    }

    private ReservaOutputDTO convertToDTO(Reserva reserva){
        ReservaOutputDTO reservaOutputDTO= new ReservaOutputDTO();
        reservaOutputDTO.setCiudadDestino(reserva.getCiudad());
        reservaOutputDTO.setNombre(reserva.getNombre());
        reservaOutputDTO.setApellidos(reserva.getApellidos());
        reservaOutputDTO.setTelefono(reserva.getTelefono());
        reservaOutputDTO.setEmail(reserva.getEmail());
        reservaOutputDTO.setFechaReserva(reserva.getFecha());
        reservaOutputDTO.setHoraReserva(reserva.getHora());
        return reservaOutputDTO;
    }

    private void checkDestinos(String destino){
         String[] destinosDisponibles= {"Valencia","Madrid","Barcelona","Bilbao"};
        if (!Arrays.asList(destinosDisponibles).contains(destino)){
            throw new UnprocesableException("Los destinos disponibles son Valencia, Madrid,Bilbao o Barcelona");
        }
    }

    private void checkHorarios(Float hora){

        if (hora.compareTo(8f)!=0 && hora.compareTo(12f)!=0  && hora.compareTo(16f)!=0  && hora.compareTo(20f)!=0 ){
            throw new UnprocesableException("Las horas disponibles son las 8h, 12h , 16h y 20h");
        }
    }


}

package com.example.Ejercicio.BackEmpresa.Reserva.application;

import com.example.Ejercicio.BackEmpresa.Autobus.application.AutobusService;
import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Autobus.domain.AutobusID;
import com.example.Ejercicio.BackEmpresa.Correo.application.CorreoService;

import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.repository.ReservaRepository;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.UnprocesableException;
import com.example.Ejercicio.BackEmpresa.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class ReservaServiceImpl implements ReservaService{

    @Autowired
    ReservaRepository reservaRepo;

    @Autowired
    AutobusService autobusService;

    @Autowired
    CorreoService correoService;

    @Autowired
    MessageProducer messageProducer;


    @Override
    public void escucharReserva(ReservaInputDTO reservaInputDTO)  {
        Reserva reserva= this.convertToReserva(reservaInputDTO);
        reservaRepo.save(reserva);
        correoService.sendEmail("Reserva Confirmada","El identificador de su reserva es "+ reserva.getIdentificador()+ "\nDestino:"+ reservaInputDTO.getCiudad()+"\nFecha Reserva:"+ reservaInputDTO.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()+"\nHora Reserva:"+reservaInputDTO.getHora(),reservaInputDTO);

    }

    @Override
    public ReservaOutputDTO hacerReserva(ReservaInputDTO reservaInputDTO)  {
       if (reservaRepo.findByCiudadAndFechaAndHoraAndEmailAndNombre(reservaInputDTO.getCiudad(),reservaInputDTO.getFecha(), reservaInputDTO.getHora(),reservaInputDTO.getEmail() ,reservaInputDTO.getNombre())!=null){
           throw new UnprocesableException("Ya hay una reserva con estos datos");
       }
        this.checkDestinos(reservaInputDTO.getCiudad());
        this.checkHorarios(reservaInputDTO.getHora());
        Date fecha=reservaInputDTO.getFecha();
        if (fecha.before(new Date(System.currentTimeMillis()))){
            throw new UnprocesableException("La fecha no puede ser anterior a la actual");
        };
        Reserva reserva= this.convertToReserva(reservaInputDTO);
        reservaRepo.save(reserva);
        correoService.sendEmail(
                "Reserva Confirmada",
                "El identificador de su reserva es "+ reserva.getIdentificador()+ "\nDestino:"+ reservaInputDTO.getCiudad()+"\nFecha Reserva:"+ reservaInputDTO.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()+"\nHora Reserva:"+reservaInputDTO.getHora(),reservaInputDTO);
        return convertToReservaDTO(reserva);


    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void actualizarReservas() {
        List<Reserva> reservas= reservaRepo.findAll();
        if (reservas.size()>0){
            for (Reserva reserva: reservas){
                ReservaInputDTO reservaInputDTO= new ReservaInputDTO(reserva);
                messageProducer.sendMessage(reservaInputDTO);
            }
        }
    }

    @Override
    public List<ReservaOutputDTO> findReservasinAutobus(String ciudad, Date fecha, Float hora)  {

        Autobus autobus= autobusService.findAutobus(ciudad, fecha, hora);
        List<Reserva> reservas= autobus.getReservas();
        return reservas.stream()
                        .map(reserva -> convertToReservaDTO(reserva))
                        .collect(Collectors.toList());
    }


    private Reserva convertToReserva(ReservaInputDTO reservaInputDTO)  {
        Reserva reserva= new Reserva();
        reserva.setCiudad(reservaInputDTO.getCiudad());
        reserva.setNombre(reservaInputDTO.getNombre());
        reserva.setApellidos(reservaInputDTO.getApellidos());
        reserva.setEmail(reservaInputDTO.getEmail());
        reserva.setTelefono(reservaInputDTO.getTelefono());
        reserva.setFecha(reservaInputDTO.getFecha());
        reserva.setHora(reservaInputDTO.getHora());
        Autobus autobus= autobusService.checkAutobus(reservaInputDTO);
        reserva.setAutobus(autobus);
        return reserva;
    }
    private ReservaOutputDTO convertToReservaDTO(Reserva reserva) {
        ReservaOutputDTO reservaOutputDTO = new ReservaOutputDTO();
        reservaOutputDTO.setIdentificador(reserva.getIdentificador().toString());
        reservaOutputDTO.setCiudadDestino(reserva.getCiudad());
        reservaOutputDTO.setNombre(reserva.getNombre());
        reservaOutputDTO.setApellidos(reserva.getApellidos());
        reservaOutputDTO.setEmail(reserva.getEmail());
        reservaOutputDTO.setTelefono(reserva.getTelefono());
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

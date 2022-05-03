package com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller;

import com.example.Ejercicio.BackWeb.Feign.Feign;

import com.example.Ejercicio.BackWeb.Reserva.application.ReservaService;
import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO.ReservaDisponibleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class ReservaController {

    @Autowired
    ReservaService reservaService;



    @Autowired
    Feign feignservice;

    @GetMapping("/test")
    public String testService(HttpServletRequest request) {

        return request.getRequestURL().toString();
    }

    @PostMapping("/reserva")
    public ResponseEntity<ReservaOutputDTO> addReserva(@RequestBody ReservaInputDTO reservaInputDTO){

        return new ResponseEntity<>(reservaService.addReserva(reservaInputDTO),HttpStatus.OK);
    }

    @PostMapping("/token")
    String login(@RequestHeader("username") String username,@RequestHeader("password") String password){

        return feignservice.login(username, password);
    }

    @GetMapping("reserva/{ciudadDestino}")
    public ResponseEntity<List<ReservaOutputDTO>> checklogin(@RequestHeader("token") String token,
                                           @PathVariable String ciudadDestino,
                                           @RequestParam(required=false, value = "fechaSuperior") @DateTimeFormat(pattern = "dd-MM-yyyy")Date fechaSuperior,
                                           @RequestParam(required=true, value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy")Date fechaInferior,
                                           @RequestParam(required=false, value = "horaInferior") Float horaInf,
                                           @RequestParam(required=false, value = "horaSuperior") Float horaSup)
    {
         ResponseEntity responseEntity= new ResponseEntity(feignservice.checkToken(token),HttpStatus.OK);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            HashMap<String,Object> data= new HashMap<>();
            data.put("ciudad",ciudadDestino);
            data.put("fechainferior",fechaInferior);
            if(fechaSuperior!=null){
                data.put("fechasuperior",fechaSuperior);
            }
            if (horaInf!=null){
                data.put("horainferior",horaInf);
            }
            if (horaSup!=null){
                data.put("horasuperior",horaSup);
            }
            System.out.println(ciudadDestino);
            System.out.println(fechaSuperior);
            return new ResponseEntity<>(reservaService.searchReservas(data),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}

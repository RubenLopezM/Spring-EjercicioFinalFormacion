package com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller;

import com.example.Ejercicio.BackEmpresa.Reserva.application.ReservaService;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping("/reserva")
    public ResponseEntity<ReservaOutputDTO> addReserva(@RequestBody ReservaInputDTO reservaInputDTO){

        return new ResponseEntity<>(reservaService.hacerReserva(reservaInputDTO), HttpStatus.OK);
    }
}

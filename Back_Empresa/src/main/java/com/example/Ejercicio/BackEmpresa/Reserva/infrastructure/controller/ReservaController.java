package com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller;

import com.example.Ejercicio.BackEmpresa.Reserva.application.ReservaService;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping("/reserva")
    public ResponseEntity<ReservaOutputDTO> addReserva(@RequestBody ReservaInputDTO reservaInputDTO) {

        return new ResponseEntity<>(reservaService.hacerReserva(reservaInputDTO), HttpStatus.OK);
    }

    @GetMapping("/reservasautobus")
    public ResponseEntity<List<ReservaOutputDTO>> findReservas(@RequestParam String ciudad,
                                               @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha,
                                               @RequestParam Float hora) throws ParseException {

            return new ResponseEntity<>(reservaService.findReservasinAutobus(ciudad, fecha, hora),HttpStatus.OK) ;
    }
}

package com.example.Ejercicio.BackEmpresa.Autobus.infrastructure.controller;

import com.example.Ejercicio.BackEmpresa.Autobus.application.AutobusService;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class AutobusController {

    @Autowired
    AutobusService autobusService;

    @GetMapping("/autobus/plazaslibres")
    public ResponseEntity<String> findPlazaslibres(@RequestParam String ciudad,
                                                               @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha,
                                                               @RequestParam Float hora)  {

        return new ResponseEntity<>(autobusService.findPlazaslibres(ciudad, fecha, hora),HttpStatus.OK) ;
    }
}

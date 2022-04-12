package com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller;

import com.example.Ejercicio.BackWeb.ReservaDisponible.application.ReservaDisponibleService;
import com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.controller.DTO.ReservaDisponibleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class ReservaDisponibleController {

    @Autowired
    ReservaDisponibleService reservaDisponibleService;

    @GetMapping("/disponible/{ciudadDestino}")
    public ResponseEntity<List<ReservaDisponibleOutputDTO>> buscarReservasDisponibles(@PathVariable String ciudadDestino,
                                                                                      @RequestParam(required = true,value = "fechaInferior")@DateTimeFormat(pattern="dd-MM-yyyy") Date fechaInferior,
                                                                                      @RequestParam(required=false, value = "fechaSuperior") @DateTimeFormat(pattern = "dd-MM-yyyy")Date fechaSuperior,
                                                                                      @RequestParam(required=false, value = "horaInferior") Float horaInf,
                                                                                      @RequestParam(required=false, value = "horaSuperior") Float horaSup){
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
        return new ResponseEntity<>(reservaDisponibleService.searchPlazas(data), HttpStatus.OK);
    }
}

package com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller;

import com.example.Ejercicio.BackEmpresa.Correo.application.CorreoService;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoInputDTO;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoOutputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaOutputDTO;
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
public class CorreoController {

    @Autowired
    CorreoService correoService;


    @GetMapping("/correos/")
    public ResponseEntity<List<CorreoOutputDTO>> buscarCorreos(@RequestParam(required = false,value = "ciudadDestino") String ciudadDestino,
                                                               @RequestParam(required = true,value = "fechaInferior")@DateTimeFormat(pattern="dd-MM-yyyy") Date fechaInferior,
                                                               @RequestParam(required=true, value = "fechaSuperior") @DateTimeFormat(pattern = "dd-MM-yyyy")Date fechaSuperior,
                                                               @RequestParam(required=false, value = "horaInferior") Float horaInf,
                                                               @RequestParam(required=false, value = "horaSuperior") Float horaSup){
        HashMap<String,Object> data= new HashMap<>();

        data.put("fechainferior",fechaInferior);
        data.put("fechasuperior",fechaSuperior);

        if (ciudadDestino!=null){
            data.put("ciudad",ciudadDestino);
        }

        if (horaInf!=null){
            data.put("horainferior",horaInf);
        }
        if (horaSup!=null){
            data.put("horasuperior",horaSup);
        }
        return new ResponseEntity<>(correoService.searchMails(data), HttpStatus.OK);
    }



}

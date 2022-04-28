package com.example.Ejercicio.BackEmpresa.Correo.application;

import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoInputDTO;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoOutputDTO;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.List;

public interface CorreoService {

    void sendEmail(String to, String subject, String text, ReservaInputDTO reservaInputDTO);
    public List<CorreoOutputDTO> searchMails(HashMap<String,Object> conditions);

}

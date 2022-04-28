package com.example.Ejercicio.BackEmpresa.Correo.application;

import com.example.Ejercicio.BackEmpresa.Correo.domain.Correo;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoInputDTO;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.controller.DTO.CorreoOutputDTO;
import com.example.Ejercicio.BackEmpresa.Correo.infrastructure.repository.CorreoRepository;
import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorreoServiceImpl implements CorreoService{

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    CorreoRepository correoRepo;

    @Override
    public void sendEmail(String to, String subject, String text, ReservaInputDTO reservaInputDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("${spring.mail.username}");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        CorreoInputDTO correoInputDTO= new CorreoInputDTO(reservaInputDTO.getCiudad(),reservaInputDTO.getEmail(),reservaInputDTO.getFecha(), reservaInputDTO.getHora());
        this.saveMail(correoInputDTO);

    }

    @Override
    public List<CorreoOutputDTO> searchMails(HashMap<String, Object> conditions) {
        List<Correo> correos= correoRepo.getData(conditions);
        List<CorreoOutputDTO> correoOutputDTOS= correos.stream().
                map((correo -> convertToDTO(correo)))
                .collect(Collectors.toList());
        return correoOutputDTOS;
    }


    private void saveMail(CorreoInputDTO correoInputDTO){
        Correo correo= new Correo();
        correo.setEmail(correoInputDTO.getEmail());
        correo.setCiudad_destino(correoInputDTO.getCiudad_destino());
        correo.setFechaReserva(correoInputDTO.getFechaReserva());
        correo.setHoraReserva(correoInputDTO.getHoraReserva());
        correoRepo.save(correo);

    }


    private CorreoOutputDTO convertToDTO(Correo correo){
        CorreoOutputDTO correoOutputDTO= new CorreoOutputDTO();
        correoOutputDTO.setCiudad_destino(correo.getCiudad_destino());
        correoOutputDTO.setEmail(correo.getEmail());
        correoOutputDTO.setFechaReserva(correo.getFechaReserva());
        correoOutputDTO.setHoraReserva(correo.getHoraReserva());
        return correoOutputDTO;
    }
}

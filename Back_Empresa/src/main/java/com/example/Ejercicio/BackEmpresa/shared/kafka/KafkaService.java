package com.example.Ejercicio.BackEmpresa.shared.kafka;


import com.example.Ejercicio.BackEmpresa.Reserva.application.ReservaService;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@Service
public class KafkaService {

   @Autowired
    ReservaService reservaService;



    @KafkaListener(topics = "actualizar",
            groupId = "group2", containerFactory
            = "reservaKafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String,ReservaInputDTO> record)  {
        System.out.println("Nueva entrada"+ record.value());
        reservaService.escucharReserva(record.value());
    }



}

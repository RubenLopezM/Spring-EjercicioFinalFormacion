package com.example.Ejercicio.BackWeb.Kafka;

import com.example.Ejercicio.BackWeb.Reserva.application.ReservaService;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository.ReservaRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaListenerService {

    @Autowired
    ReservaService reservaService;

    @Autowired
    ReservaRepository reservaRepo;

    @KafkaListener(topics = "actualizar",
            groupId = "group", containerFactory
            = "reservaKafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, ReservaInputDTO> record)
    {
        System.out.println("Nueva reserva"+ record.value());
        reservaService.listenReserva(record.value());

    }
}

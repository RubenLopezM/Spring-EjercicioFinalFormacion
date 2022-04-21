package com.example.Ejercicio.BackEmpresa.shared.kafka;

import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @KafkaListener(topics = "actualizar",
            groupId = "group", containerFactory
            = "reservaKafkaListenerContainerFactory")
    public void
    publish(ReservaInputDTO reservaInputDTO)
    {
        System.out.println("New Entry: "
                + reservaInputDTO);
    }
}

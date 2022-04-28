package com.example.Ejercicio.BackEmpresa.shared.kafka;

import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {


    @Autowired
    private KafkaTemplate<String, ReservaInputDTO> reservaKafkaTemplate;

    private static final String TOPIC = "actualizar";

    public void sendMessage(ReservaInputDTO reservaInputDTO) {

        ProducerRecord producerRecord= new ProducerRecord<> (TOPIC,reservaInputDTO);
        producerRecord.headers().add("application","backempresa".getBytes());


        ListenableFuture<SendResult<String, ReservaInputDTO>> future =
                reservaKafkaTemplate.send(producerRecord);


        future.addCallback(new ListenableFutureCallback<SendResult<String, ReservaInputDTO>>() {

            @Override
            public void onSuccess(SendResult<String, ReservaInputDTO> result) {
                System.out.println("Sent message=[" + reservaInputDTO +
                        "] with offset=[" + result.getRecordMetadata().offset() + "] and"+ new String(result.getProducerRecord().headers().lastHeader("application").value())) ;

            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + reservaInputDTO + "] due to : " + ex.getMessage());
            }
        });
    }
}

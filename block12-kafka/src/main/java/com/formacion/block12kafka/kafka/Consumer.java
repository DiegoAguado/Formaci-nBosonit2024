package com.formacion.block12kafka.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class Consumer {

    @KafkaListener(topics = "mensaje")
    public void listenTopic(String message) {
        System.out.println(message);
    }
}

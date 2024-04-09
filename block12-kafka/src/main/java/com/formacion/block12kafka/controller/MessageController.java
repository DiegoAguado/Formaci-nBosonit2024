package com.formacion.block12kafka.controller;

import com.formacion.block12kafka.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    Producer producer;
    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @PostMapping("/send/{mensaje}")
    public void sendMessage(@PathVariable String mensaje) {
        producer.sendMessage(topicName, mensaje);
    }
}

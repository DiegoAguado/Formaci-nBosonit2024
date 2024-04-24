package com.formacion.backempresa.kafka;


import com.formacion.backempresa.repository.ReservaDisponibleRepository;
import com.virtualtravel.common.ReservaDisponibleDto;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("sincronizacion")
    public String topic;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Autowired
    ReservaDisponibleRepository repository;

    @Bean
    public KafkaTemplate<String, ReservaDisponibleDto> sincronizacionKafkaTemplate() {
        return new KafkaTemplate<>(sincronizacionProducerFactory());
    }

    @Bean
    public ProducerFactory<String, ReservaDisponibleDto> sincronizacionProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public void sendSincronizacion() {
        repository.findAll().forEach(i -> sincronizacionKafkaTemplate().send(topic, i.transform()));
    }
}

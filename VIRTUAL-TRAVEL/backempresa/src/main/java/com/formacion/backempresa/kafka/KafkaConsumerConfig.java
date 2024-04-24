package com.formacion.backempresa.kafka;

import com.formacion.backempresa.controller.ReservaDisponibleController;
import com.formacion.backempresa.controller.dto.ReservaOutputDto;
import com.virtualtravel.common.ReservaOutput;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Autowired
    ReservaDisponibleController controller;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    public Map<String, Object> consumerConfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, true);
        return props;
    }

    @Bean
    ConsumerFactory<String, ReservaOutput> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ReservaOutput.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReservaOutput> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReservaOutput> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "reservas")
    public void listenReserva(ReservaOutput reserva) {
        ReservaOutputDto reservaOutputDto = new ReservaOutputDto();
        reservaOutputDto.setId(reserva.getId());
        reservaOutputDto.setCiudadDestino(reserva.getCiudadDestino());
        reservaOutputDto.setNombre(reserva.getNombre());
        reservaOutputDto.setApellido(reserva.getApellido());
        reservaOutputDto.setTelefono(reserva.getTelefono());
        reservaOutputDto.setEmail(reserva.getEmail());
        reservaOutputDto.setFechaReserva(reserva.getFechaReserva());
        reservaOutputDto.setHoraReserva(reserva.getHoraReserva());

        controller.updatePlazas(reservaOutputDto);
    }
}

package com.formacion.backweb.kafka;


import com.formacion.backweb.domain.ReservaDisponible;
import com.formacion.backweb.repository.ReservaDisponibleRepository;
import com.virtualtravel.common.ReservaDisponibleDto;
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
    ReservaDisponibleRepository reservaDisponibleRepository;

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
    ConsumerFactory<String, ReservaDisponibleDto> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ReservaDisponibleDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReservaDisponibleDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReservaDisponibleDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "sincronizacion")
    public void listenReservasDisponibles(ReservaDisponibleDto reservaDisponibleDto) {
        System.out.println(reservaDisponibleDto);
        reservaDisponibleRepository.save(new ReservaDisponible(reservaDisponibleDto));
    }
}

package com.bosonit.formacion;

import com.bosonit.formacion.model.Persona;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    Persona bean1(){
        Persona persona = new Persona();
        persona.setNombre("bean1");
        return persona;
    }

    @Bean
    Persona bean2(){
        Persona persona = new Persona();
        persona.setNombre("bean2");
        return persona;
    }

    @Bean
    Persona bean3(){
        Persona persona = new Persona();
        persona.setNombre("bean3");
        return persona;
    }
}

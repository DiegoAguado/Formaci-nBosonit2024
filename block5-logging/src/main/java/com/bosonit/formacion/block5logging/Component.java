package com.bosonit.formacion.block5logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@org.springframework.stereotype.Component
public class Component {
    @Bean
    public void logs(){
        log.trace("Mensaje a nivel de TRACE");
        log.debug("Mensaje a nivel de DEBUG");
        log.info("Mensaje a nivel de INFO");
        log.warn("Mensaje a nivel de WARNING");
        log.error("Mensaje a nivel de ERROR");
    }
}

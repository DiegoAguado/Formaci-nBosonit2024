package com.bosonit.formacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseSecundaria implements CommandLineRunner {

    @Override
    public void run(String... args){
        System.out.println("Hola desde clase secundaria");
    }

}

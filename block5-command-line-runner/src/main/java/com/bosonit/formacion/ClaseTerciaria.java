package com.bosonit.formacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseTerciaria implements CommandLineRunner {

    @Override
    public void run(String... args){
        for(String arg:args){
            System.out.println(arg);
        }
    }
}

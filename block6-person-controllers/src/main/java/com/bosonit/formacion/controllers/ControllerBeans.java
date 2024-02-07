package com.bosonit.formacion.controllers;

import com.bosonit.formacion.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;

@RestController
public class ControllerBeans {
    private Persona persona1;
    private Persona persona2;
    private Persona persona3;

    public ControllerBeans(@Qualifier("bean1") Persona persona1, @Qualifier("bean2") Persona persona2, @Qualifier("bean3") Persona persona3){
        this.persona1 = persona1;
        this.persona2 = persona2;
        this.persona3 = persona3;
    }
    @GetMapping("/controlador/bean/{bean}")
    public Persona getBean(@PathVariable String bean){
        switch(bean){
            case "bean1":
                return persona1;
            case "bean2":
                return persona2;
            case "bean3":
                return persona3;
            default:
                return null;
        }
    }
}

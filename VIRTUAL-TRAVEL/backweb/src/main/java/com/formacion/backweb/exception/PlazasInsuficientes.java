package com.formacion.backweb.exception;

public class PlazasInsuficientes extends RuntimeException{
    public PlazasInsuficientes(){
        super("No hay plazas disponibles en este momento");
    }
}

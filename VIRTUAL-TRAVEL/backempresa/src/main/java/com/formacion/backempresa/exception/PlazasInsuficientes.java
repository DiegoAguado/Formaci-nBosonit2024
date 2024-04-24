package com.formacion.backempresa.exception;

public class PlazasInsuficientes extends RuntimeException{
    public PlazasInsuficientes(){
        super("No hay plazas disponibles en este momento");
    }
    public PlazasInsuficientes(String msg){
        super(msg);
    }
}

package com.formacion.backweb.exception;

public class ReservaNoEncontrada extends RuntimeException{
    public ReservaNoEncontrada(){
        super("Reserva no encontrada");
    }
}

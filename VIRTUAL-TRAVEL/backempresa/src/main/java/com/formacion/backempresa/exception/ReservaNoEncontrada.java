package com.formacion.backempresa.exception;

public class ReservaNoEncontrada extends RuntimeException{
    public ReservaNoEncontrada(){
        super("Reserva no encontrada");
    }
}

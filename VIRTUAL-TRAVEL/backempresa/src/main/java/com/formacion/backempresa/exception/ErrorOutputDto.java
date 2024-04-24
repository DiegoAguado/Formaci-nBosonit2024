package com.formacion.backempresa.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorOutputDto{
    int httpCode;
    String msgError;
    String tipo;
    Date fecha;
}

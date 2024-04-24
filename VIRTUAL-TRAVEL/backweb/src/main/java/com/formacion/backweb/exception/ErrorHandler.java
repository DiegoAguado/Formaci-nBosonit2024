package com.formacion.backweb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    Error error = new Error();

    @ExceptionHandler(PlazasInsuficientes.class)
    public ResponseEntity<Error> handleErrorOutputDto(PlazasInsuficientes e){
        error.setHttpCode(400);
        error.setMsgError(e.getMessage());
        error.setTipo("Fatal");
        error.setFecha(new Date());

        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

    @ExceptionHandler(ReservaNoEncontrada.class)
    public ResponseEntity<Error> handleErrorOutputDto(ReservaNoEncontrada e){
        error.setHttpCode(404);
        error.setMsgError(e.getMessage());
        error.setTipo("Info");
        error.setFecha(new Date());

        return ResponseEntity.status(error.getHttpCode()).body(error);
    }
}

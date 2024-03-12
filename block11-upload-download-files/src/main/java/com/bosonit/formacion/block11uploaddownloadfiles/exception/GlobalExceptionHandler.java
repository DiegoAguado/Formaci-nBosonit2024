package com.bosonit.formacion.block11uploaddownloadfiles.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    CustomError error = new CustomError();

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(StorageException e){
        error.setTimestamp(new Date());
        error.setHttpCode(500);
        error.setMensaje(e.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(StorageFileNotFoundException e){
        error.setTimestamp(new Date());
        error.setHttpCode(404);
        error.setMensaje(e.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException e){
        error.setTimestamp(new Date());
        error.setHttpCode(404);
        error.setMensaje(e.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }
}

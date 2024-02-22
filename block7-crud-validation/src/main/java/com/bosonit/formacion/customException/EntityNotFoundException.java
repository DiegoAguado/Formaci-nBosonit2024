package com.bosonit.formacion.customException;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(){
         super("Id no encontrado");
     }

    public EntityNotFoundException(String mensaje){super(mensaje);}
}

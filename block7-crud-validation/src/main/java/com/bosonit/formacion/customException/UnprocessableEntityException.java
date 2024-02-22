package com.bosonit.formacion.customException;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends Exception{

    public UnprocessableEntityException(){
        super("Unprocessable entity");
    }
    public UnprocessableEntityException(String mensaje){super(mensaje);}
}

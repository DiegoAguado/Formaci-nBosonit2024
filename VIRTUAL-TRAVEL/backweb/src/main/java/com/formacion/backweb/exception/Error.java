package com.formacion.backweb.exception;

import lombok.Data;
import java.util.Date;

@Data
public class Error {
    int httpCode;
    String msgError;
    String tipo;
    Date fecha;

    public ErrorOutputDto errorToErrorOutputDto(){
        return new ErrorOutputDto(
                this.httpCode,
                this.msgError,
                this.tipo,
                this.fecha
        );
    }
}

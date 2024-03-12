package com.bosonit.formacion.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class FicheroOutputDto {
    private int id;
    private String nombre;
    private Date fechaSubida;
    private String categoria;
}

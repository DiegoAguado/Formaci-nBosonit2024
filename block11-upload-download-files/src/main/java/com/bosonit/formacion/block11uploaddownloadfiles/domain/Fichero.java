package com.bosonit.formacion.block11uploaddownloadfiles.domain;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private Date fechaSubida;
    private String categoria;

    public Fichero(String nombre, Date fechaSubida, String categoria){
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.categoria = categoria;
    }

    public FicheroOutputDto ficheroToFicheroOutputDto(){
        return new FicheroOutputDto(
                this.id,
                this.nombre,
                this.fechaSubida,
                this.categoria
        );
    }
}

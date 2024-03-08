package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.Provincia.dto.ProvinciaInputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    private String codigo;
    private String nombre;
    @OneToMany(mappedBy = "codProvincia")
    private Set<Cliente> clientes;

    public Provincia(ProvinciaInputDto provincia){
        this.codigo = provincia.getCodigo();
        this.nombre = provincia.getNombre();
    }

    public ProvinciaOutputDto provinciaToProvinciaOutputDto(){
        return new ProvinciaOutputDto(codigo, nombre, clientes);
    }
}

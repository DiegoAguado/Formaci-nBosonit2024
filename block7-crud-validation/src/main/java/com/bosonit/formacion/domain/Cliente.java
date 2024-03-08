package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    private String dni;
    private String nombre;
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia codProvincia;

    public Cliente(ClienteInputDto cliente){
        this.dni = cliente.getDni();
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
    }

    public ClienteOutputDto clienteToClienteOutputDto(){
        return new ClienteOutputDto(
                dni,
                nombre,
                direccion,
                codProvincia.getCodigo()
        );
    }
}

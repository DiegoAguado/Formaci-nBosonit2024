package com.bosonit.formacion.controller.Provincia.dto;

import com.bosonit.formacion.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaOutputDto {
    String codigo;
    String nombre;
    Set<Cliente> clientes;
}

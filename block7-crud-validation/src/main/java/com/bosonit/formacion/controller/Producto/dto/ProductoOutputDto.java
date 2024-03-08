package com.bosonit.formacion.controller.Producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoOutputDto {
    String id;
    String descripcion;
    float precio;
}

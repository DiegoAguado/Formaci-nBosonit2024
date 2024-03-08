package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.Producto.dto.ProductoInputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    private String id;
    private String descripcion;
    private float precio;

    public Producto(ProductoInputDto producto){
        this.id = producto.getId();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
    }

    public ProductoOutputDto productoToProductoOutputDto(){
        return new ProductoOutputDto(id, descripcion, precio);
    }
}

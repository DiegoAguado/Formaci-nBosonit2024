package com.bosonit.formacion.application.Producto;

import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoInputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;

import java.util.List;

public interface ProductoService {
    ProductoOutputDto addProducto(ProductoInputDto producto);
    ProductoOutputDto getProductoById(String id) throws EntityNotFoundException;
    List<ProductoOutputDto> getAllProductos(int pageNumber, int pageSize);
    ProductoOutputDto getProductoByNombreLike(String nombre);
    ProductoOutputDto updateProducto(ProductoInputDto producto) throws EntityNotFoundException;
    void deleteProductoById(String id) throws EntityNotFoundException;
}

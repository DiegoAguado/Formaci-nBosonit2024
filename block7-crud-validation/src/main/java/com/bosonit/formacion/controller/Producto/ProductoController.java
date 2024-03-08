package com.bosonit.formacion.controller.Producto;

import com.bosonit.formacion.application.Producto.ProductoService;
import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoInputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoOutputDto> addProducto(@RequestBody ProductoInputDto producto) {
        return ResponseEntity.ok().body(productoService.addProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoOutputDto> getProductoById(@PathVariable String id) throws EntityNotFoundException {
        return ResponseEntity.ok().body(productoService.getProductoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductoOutputDto>> getAllProductos(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(productoService.getAllProductos(pageNumber, pageSize));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductoOutputDto> getProductoByNombre(@PathVariable String nombre){
        return ResponseEntity.ok().body(productoService.getProductoByNombreLike(nombre));
    }

    @PutMapping
    public ResponseEntity<ProductoOutputDto> updateProducto(@RequestBody ProductoInputDto producto) throws EntityNotFoundException{
        return ResponseEntity.ok().body(productoService.updateProducto(producto));
    }

    @DeleteMapping("/{id}")
    public void deleteProductoById(@PathVariable String id) throws EntityNotFoundException{
        productoService.deleteProductoById(id);
    }
}

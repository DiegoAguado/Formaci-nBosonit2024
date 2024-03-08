package com.bosonit.formacion.application.Producto;

import com.bosonit.formacion.controller.Producto.dto.ProductoInputDto;
import com.bosonit.formacion.controller.Producto.dto.ProductoOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.domain.Producto;
import com.bosonit.formacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    ProductoRepository productoRepository;
    @Override
    public ProductoOutputDto addProducto(ProductoInputDto producto){
        return productoRepository.save(new Producto(producto)).productoToProductoOutputDto();
    }
    @Override
    public ProductoOutputDto getProductoById(String id) throws EntityNotFoundException{
        return productoRepository.findById(id).orElseThrow(EntityNotFoundException::new).productoToProductoOutputDto();
    }
    @Override
    public List<ProductoOutputDto> getAllProductos(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return  productoRepository.findAll(pageRequest).stream().map(Producto::productoToProductoOutputDto).toList();
    }
    @Override
    public ProductoOutputDto getProductoByNombreLike(String nombre){
        return productoRepository.findProductoByNombreLike(nombre).productoToProductoOutputDto();
    }
    @Override
    public ProductoOutputDto updateProducto(ProductoInputDto producto) throws EntityNotFoundException{
        productoRepository.findById(producto.getId()).orElseThrow(EntityNotFoundException::new);
        return productoRepository.save(new Producto(producto)).productoToProductoOutputDto();
    }
    @Override
    public void deleteProductoById(String id) throws EntityNotFoundException{
        productoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productoRepository.deleteById(id);
    }
}

package com.bosonit.formacion.application.Cliente;

import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;

import java.util.List;

public interface ClienteService {
    ClienteOutputDto addCliente(ClienteInputDto cliente) throws EntityNotFoundException;
    ClienteOutputDto getClienteById(String id) throws EntityNotFoundException;
    List<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize);
    List<ClienteOutputDto> getAllClientesByNombreLike(String nombre, int pageNumber, int pageSize);
    List<ClienteOutputDto> getAllClientesByProvinciaLike(String provincia, int pageNumber, int pageSize);
    ClienteOutputDto updateCliente(ClienteInputDto cliente) throws EntityNotFoundException;
    void deleteClienteById(String id) throws EntityNotFoundException;
}

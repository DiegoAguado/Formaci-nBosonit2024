package com.bosonit.formacion.application.Cliente;

import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaInputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.domain.Cliente;
import com.bosonit.formacion.repository.ClienteRepository;
import com.bosonit.formacion.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInput) throws EntityNotFoundException{
        Cliente cliente = new Cliente(clienteInput);
        cliente.setCodProvincia(provinciaRepository.findById(clienteInput.getCod_provincia()).orElseThrow(EntityNotFoundException::new));
        return clienteRepository.save(cliente).clienteToClienteOutputDto();
    }
    @Override
    public ClienteOutputDto getClienteById(String id) throws EntityNotFoundException{
        return clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new).clienteToClienteOutputDto();
    }
    @Override
    public List<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return clienteRepository.findAll(pageRequest).getContent().stream().map(Cliente::clienteToClienteOutputDto).toList();
    }
    @Override
    public List<ClienteOutputDto> getAllClientesByNombreLike(String nombre, int pageNumber, int pageSize){
        return clienteRepository.findByNombreLike(nombre).stream().map(Cliente::clienteToClienteOutputDto).toList();
    }

    @Override
    public List<ClienteOutputDto> getAllClientesByProvinciaLike(String provincia, int pageNumber, int pageSize){
        return clienteRepository.findByProvinciaLike(provincia).stream().map(Cliente::clienteToClienteOutputDto).toList();
    }
    @Override
    public ClienteOutputDto updateCliente(ClienteInputDto cliente) throws EntityNotFoundException{
        clienteRepository.findById(cliente.getDni()).orElseThrow(EntityNotFoundException::new);
        return clienteRepository.save(new Cliente(cliente)).clienteToClienteOutputDto();
    }
    @Override
    public void deleteClienteById(String id) throws EntityNotFoundException{
        clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        clienteRepository.deleteById(id);
    }
}

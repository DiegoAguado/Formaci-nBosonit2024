package com.bosonit.formacion.application.Provincia;

import com.bosonit.formacion.controller.Provincia.dto.ProvinciaInputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.domain.Provincia;
import com.bosonit.formacion.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinciaServiceImpl implements ProvinciaService{
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Override
    public ProvinciaOutputDto addProvincia(ProvinciaInputDto provincia){
        return provinciaRepository.save(new Provincia(provincia)).provinciaToProvinciaOutputDto();
    }
    @Override
    public ProvinciaOutputDto getProvinciaById(String codigo) throws EntityNotFoundException{
        return provinciaRepository.findById(codigo).orElseThrow(EntityNotFoundException::new).provinciaToProvinciaOutputDto();
    }
    @Override
    public List<ProvinciaOutputDto> getAllProvincias(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return provinciaRepository.findAll(pageRequest).stream().map(Provincia::provinciaToProvinciaOutputDto).toList();
    }
    @Override
    public List<ProvinciaOutputDto> getAllProvinciasByNombreLike(String nombre, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return provinciaRepository.findProvinciaByNombreLike(nombre, pageRequest).stream().map(Provincia::provinciaToProvinciaOutputDto).toList();
    }
    @Override
    public ProvinciaOutputDto updateProvincia(ProvinciaInputDto provincia) throws EntityNotFoundException{
        provinciaRepository.findById(provincia.getCodigo()).orElseThrow(EntityNotFoundException::new);
        return provinciaRepository.save(new Provincia(provincia)).provinciaToProvinciaOutputDto();
    }
    @Override
    public void deleteProvinciaById(String codigo) throws EntityNotFoundException{
        provinciaRepository.findById(codigo).orElseThrow(EntityNotFoundException::new);
        provinciaRepository.deleteById(codigo);
    }
}

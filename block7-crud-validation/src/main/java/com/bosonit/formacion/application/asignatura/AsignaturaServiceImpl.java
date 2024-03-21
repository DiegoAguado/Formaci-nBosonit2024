package com.bosonit.formacion.application.asignatura;

import com.bosonit.formacion.controller.asignatura.dto.AsignaturaInputDto;
import com.bosonit.formacion.controller.asignatura.dto.AsignaturaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.domain.Asignatura;
import com.bosonit.formacion.repository.AsignaturaRepository;
import com.bosonit.formacion.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInput) {
        Asignatura asignatura = new Asignatura(asignaturaInput);

        return asignaturaRepository.save(asignatura).asignaturaToAsignaturaOutputDto();
    }
    @Override
    public AsignaturaOutputDto getAsignaturaById(String id) throws EntityNotFoundException{
        return asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new).asignaturaToAsignaturaOutputDto();
    }
    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return asignaturaRepository.findAll(pageRequest).getContent().stream().map(Asignatura::asignaturaToAsignaturaOutputDto).toList();
    }
    @Override
    public List<AsignaturaOutputDto> getAllAsignaturasEstudiante(String id_student, int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return asignaturaRepository.findAllAsignaturasEstudianteLike(id_student, pageRequest).getContent().stream().map(Asignatura::asignaturaToAsignaturaOutputDto).toList();
    }
    @Override
    public AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignatura) throws EntityNotFoundException{
        asignaturaRepository.findById(asignatura.getId_asignatura()).orElseThrow(EntityNotFoundException::new);

        return asignaturaRepository.save(new Asignatura(asignatura)).asignaturaToAsignaturaOutputDto();
    }
    @Override
    public void deleteAsignaturaById(String id) throws EntityNotFoundException{
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        asignatura.getStudent().forEach(s-> studentRepository.findById(s.getId_student()).orElseThrow(null).getAsignatura().clear());
        asignatura.getStudent().clear();
        asignaturaRepository.deleteById(id);
    }
}

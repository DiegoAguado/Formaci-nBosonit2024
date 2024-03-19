package com.formacion.block13mongodb.application;

import com.formacion.block13mongodb.controller.dto.PersonInputDto;
import com.formacion.block13mongodb.controller.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto getPersonById(int id);
    List<PersonOutputDto> getAll(int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person);
    void deletePersonById(int id);
}

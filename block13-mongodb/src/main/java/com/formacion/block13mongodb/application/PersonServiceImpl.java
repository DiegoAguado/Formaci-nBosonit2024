package com.formacion.block13mongodb.application;

import com.formacion.block13mongodb.controller.dto.PersonInputDto;
import com.formacion.block13mongodb.controller.dto.PersonOutputDto;
import com.formacion.block13mongodb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    MongoTemplate template;
    @Override
    public PersonOutputDto addPerson(PersonInputDto person){
        return template.save(new Person(person)).personaToPersonaOutputDto();
    }
    @Override
    public PersonOutputDto getPersonById(int id){
        return template.findById(id, Person.class).personaToPersonaOutputDto();
    }
    @Override
    public List<PersonOutputDto> getAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Query query = new Query().with(pageable);
        return template.find(query, Person.class).stream().map(Person::personaToPersonaOutputDto).toList();
    }
    @Override
    public PersonOutputDto updatePerson(PersonInputDto person){
        return template.save(new Person(person)).personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonById(int id){
        Query query = new Query(Criteria.where("_id").is(id));
        template.remove(template.findById(id, Person.class));
    }
}

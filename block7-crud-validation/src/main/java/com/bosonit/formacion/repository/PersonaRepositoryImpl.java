package com.bosonit.formacion.repository;

import com.bosonit.formacion.controller.Persona.dto.PersonaOutputDto;
import com.bosonit.formacion.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<PersonaOutputDto> searchPersonas(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field){
                case "usuario":
                    predicates.add(cb.like(root.get(field), "%" + (String)value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String)value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String)value + "%"));
                    break;
                case "created_date":
                    String datecondition = (String)conditions.get("datecondition");
                    if(datecondition!=null){
                        switch (datecondition){
                            case "greater":
                                predicates.add(cb.greaterThan(root.get("created_date"), (Date)value));
                                break;
                            case "less":
                                predicates.add(cb.lessThan(root.get("created_date"), (Date)value));
                                break;
                        }
                    }
                    break;
            }
        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        String orderBy = (String)conditions.get("orderBy");
        if(orderBy!=null){
            switch (orderBy){
                case "usuario":
                    query.orderBy(cb.desc(root.get("usuario")));
                    break;
                case "name":
                    query.orderBy(cb.desc(root.get("name")));
                    break;
            }
        }

        return entityManager.createQuery(query)
                .setMaxResults((int)conditions.get("pageSize"))
                .setFirstResult((int)conditions.get("pageNumber") * (int)conditions.get("pageSize"))
                .getResultList()
                .stream().map(Persona::personaToPersonaOutputDto).toList();
    }
}

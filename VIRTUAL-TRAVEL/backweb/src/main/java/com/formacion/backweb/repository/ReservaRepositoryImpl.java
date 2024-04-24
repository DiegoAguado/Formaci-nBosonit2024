package com.formacion.backweb.repository;

import com.formacion.backweb.domain.Reserva;
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

public class ReservaRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<Reserva> getByCiudadDestino(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reserva> query = cb.createQuery(Reserva.class);
        Root<Reserva> root = query.from(Reserva.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) ->{
            switch(field){
                case "ciudadDestino":
                    predicates.add(cb.like(root.get(field), (String)value));
                    break;
                case "fechaInferior":
                    predicates.add(cb.lessThan(root.get("fechaReserva"), (Date)value));
                    break;
                case "fechaSuperior":
                    predicates.add(cb.greaterThan(root.get("fechaReserva"), (Date)value));
                    break;
                case "horaInferior":
                    predicates.add(cb.lessThan(root.get("horaReserva"), (Float)value));
                    break;
                case "horaSuperior":
                    predicates.add(cb.greaterThan(root.get("horaReserva"), (Float)value));
                    break;
            }
        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query)
                .getResultList();
    }
}

package com.formacion.backweb.repository;

import com.formacion.backweb.domain.ReservaDisponible;
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

public class ReservaDisponibleRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<ReservaDisponible> consultarDisponibilidad(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservaDisponible> query = cb.createQuery(ReservaDisponible.class);
        Root<ReservaDisponible> root = query.from(ReservaDisponible.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) ->{
            switch(field){
                case "ciudadDestino":
                    predicates.add(cb.like(root.get(field), (String)value));
                    break;
                case "fechaInferior":
                    predicates.add(cb.lessThan(root.get("fechaSalida"), (Date)value));
                    break;
                case "fechaSuperior":
                    predicates.add(cb.greaterThan(root.get("fechaSalida"), (Date)value));
                    break;
                case "horaInferior":
                    predicates.add(cb.lessThan(root.get("horaSalida"), (Float)value));
                    break;
                case "horaSuperior":
                predicates.add(cb.greaterThan(root.get("horaSalida"), (Float)value));
                break;
            }
        });
        predicates.add(cb.greaterThan(root.get("numeroPlazas"), 0));

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query)
                .getResultList();
    }
}

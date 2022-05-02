package com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.repository;

import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponibleID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ReservaDisponibleRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ReservaDisponible> getData(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservaDisponible> query= cb.createQuery(ReservaDisponible.class);
        Root<ReservaDisponible> root = query.from(ReservaDisponible.class);
        System.out.println("Esto es"+root.get("id").get("ciudad"));

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "fechainferior":
                   predicates.add(cb.greaterThanOrEqualTo(root.get("id").get("fecha"),(Date)value));
                   break;
                case "fechasuperior":
                    predicates.add(cb.lessThanOrEqualTo(root.get("id").get("fecha"),(Date)value));
                    break;
                case "ciudad":
                    predicates.add(cb.equal(root.get("id").get(field),(String)value));
                    break;
                case "horainferior":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("id").get("hora"),(Float)value));
                    break;
                case "horasuperior":
                    predicates.add(cb.lessThanOrEqualTo(root.get("id").get("hora"),(Float)value));
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}

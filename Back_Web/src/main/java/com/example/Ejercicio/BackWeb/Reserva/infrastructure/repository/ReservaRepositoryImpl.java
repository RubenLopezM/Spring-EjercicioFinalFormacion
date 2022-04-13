package com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository;

import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;

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

public class ReservaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Reserva> getReservas(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reserva> query= cb.createQuery(Reserva.class);
        Root<Reserva> root = query.from(Reserva.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "fechainferior":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("fechaReserva"),(Date)value));
                    break;
                case "fechasuperior":
                    predicates.add(cb.lessThanOrEqualTo(root.get("fechaReserva"),(Date)value));
                    break;
                case "ciudad":
                    predicates.add(cb.equal(root.get("ciudad_destino"),(String)value));
                    break;
                case "horainferior":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("horaReserva"),(Float)value));
                    break;
                case "horasuperior":
                    predicates.add(cb.lessThanOrEqualTo(root.get("horaReserva"),(Float)value));
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}

package com.example.Ejercicio.BackWeb.ReservaDisponible.infrastructure.repository;

import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponibleID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ReservaDisponibleRepository extends JpaRepository<ReservaDisponible, ReservaDisponibleID> {

    public List<ReservaDisponible> getData(HashMap<String, Object> conditions);
    public List<ReservaDisponible> findByIdCiudad(String ciudad);

}

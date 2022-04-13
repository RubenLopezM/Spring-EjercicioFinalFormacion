package com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository;

import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import com.example.Ejercicio.BackWeb.ReservaDisponible.domain.ReservaDisponible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,String> {
    public List<Reserva> getReservas(HashMap<String, Object> conditions);
}

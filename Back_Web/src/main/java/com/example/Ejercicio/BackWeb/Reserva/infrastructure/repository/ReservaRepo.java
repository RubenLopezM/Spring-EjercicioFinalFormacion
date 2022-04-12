package com.example.Ejercicio.BackWeb.Reserva.infrastructure.repository;

import com.example.Ejercicio.BackWeb.Reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepo extends JpaRepository<Reserva,String> {
}

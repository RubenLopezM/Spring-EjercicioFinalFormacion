package com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.repository;

import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    public Reserva findByCiudadAndFechaAndHoraAndEmailAndNombre (String ciudad, Date fecha, Float hora, String email, String nombre);
}

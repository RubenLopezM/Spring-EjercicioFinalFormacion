package com.example.Ejercicio.BackEmpresa.Autobus.infrastructure.repository;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Autobus.domain.AutobusID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus, AutobusID> {
}

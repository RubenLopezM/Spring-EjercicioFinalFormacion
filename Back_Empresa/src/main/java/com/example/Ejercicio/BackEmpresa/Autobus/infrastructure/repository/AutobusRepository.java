package com.example.Ejercicio.BackEmpresa.Autobus.infrastructure.repository;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Autobus.domain.AutobusID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus, AutobusID> {

}

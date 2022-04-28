package com.example.Ejercicio.BackEmpresa.Correo.infrastructure.repository;

import com.example.Ejercicio.BackEmpresa.Correo.domain.Correo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CorreoRepository extends JpaRepository<Correo,String> {
    public List<Correo> getData(HashMap<String,Object> conditions);

}

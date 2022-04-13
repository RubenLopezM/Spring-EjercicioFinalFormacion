package com.example.Ejercicio.BackEmpresa.Usuario.infrastructure.repository;

import com.example.Ejercicio.BackEmpresa.Usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsuarioRepo extends JpaRepository<Usuario,String> {
   Optional<Usuario>  findByUsername(String username);
}

package com.example.Ejercicio.BackEmpresa.Usuario.application;


import com.example.Ejercicio.BackEmpresa.Usuario.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(String id);
    Usuario findByUsername(String username);
    Usuario add(Usuario usuario);
    void del(String id);
}

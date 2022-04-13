package com.example.Ejercicio.BackEmpresa.Usuario.application;


import com.example.Ejercicio.BackEmpresa.Usuario.domain.Usuario;
import com.example.Ejercicio.BackEmpresa.Usuario.infrastructure.repository.UsuarioRepo;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.NotFoundException;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepo usuarioRepo;


    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario findById(String id) {
        return usuarioRepo.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el usuario"));
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepo.findByUsername(username).orElseThrow(()-> new NotFoundException("No se ha encontrado el usuario"));
    }

    @Override
    public Usuario add(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public void del(String id) {
        Usuario usuario= usuarioRepo.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado el usuario"));
        usuarioRepo.delete(usuario);

    }


}

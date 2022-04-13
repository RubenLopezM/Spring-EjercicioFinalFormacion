package com.example.Ejercicio.BackEmpresa.Usuario.infrastructure.controller;

import com.example.Ejercicio.BackEmpresa.Usuario.application.UsuarioService;
import com.example.Ejercicio.BackEmpresa.Usuario.domain.Usuario;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.NotFoundException;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.UnprocesableException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0")
public class TokenController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("token")
    public ResponseEntity<String> login(
            @RequestHeader("username") String username,
            @RequestHeader("password") String pwd)
            throws NotFoundException, UnprocesableException
    {


       Usuario usuario = usuarioService.findByUsername(username);
        if (!usuario.getPassword().equals(pwd)) throw new UnprocesableException("El password no es correcto");
        return new ResponseEntity<>(getJWTToken(username,"ROLE_ADMIN"), HttpStatus.OK);
    }

    @GetMapping("token/{token}")
    public ResponseEntity<Void> checkToken(@PathVariable String token){
        if (this.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private String getJWTToken(String username, String rol)
    {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

    private boolean verifyToken(String token){
        final String SECRET = "mySecretKey";
        final String PREFIX = "Bearer ";
        try {
            String jwtToken = token.replace(PREFIX,"");
            Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}

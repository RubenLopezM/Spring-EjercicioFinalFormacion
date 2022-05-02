package com.example.Ejercicio.BackEmpresa;


import com.example.Ejercicio.BackEmpresa.Usuario.application.UsuarioService;
import com.example.Ejercicio.BackEmpresa.Usuario.domain.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class BackEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEmpresaApplication.class, args);
	}



	@Bean
	CommandLineRunner run(UsuarioService usuarioService) {
		return args -> {
			usuarioService.add(new Usuario(null,"rublop","1234"));

		};
	}


}

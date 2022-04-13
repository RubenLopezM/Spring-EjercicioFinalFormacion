package com.example.Ejercicio.BackWeb;

import feign.RequestInterceptor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class BackWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackWebApplication.class, args);
	}



}

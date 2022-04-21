package com.example.Ejercicio.BackWeb.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "feign",url = "http://localhost:8090/api/v0")
public interface Feign  {

    @PostMapping("/token")
    String login(@RequestHeader("username") String username,@RequestHeader("password") String password);

    @GetMapping("/token/{token}")
    String checkToken(@PathVariable("token") String token);
}

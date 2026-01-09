package es.fplumara.dam2.springdemo.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String sayHello() {
        return "Hola desde el servicio";
    }
}

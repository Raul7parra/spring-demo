package es.fplumara.dam2.springdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    public String sayHello() {
        log.debug("Entrando en sayHello()");
        String msg = "Hola desde el servicio";
        log.info("Mensaje generado: {}", msg);
        return msg;
    }
}




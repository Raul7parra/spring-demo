package es.fplumara.dam2.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
}

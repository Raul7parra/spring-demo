package es.fplumara.dam2.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // API básica (luego lo afinamos con JWT si queréis)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello", "/info").permitAll()

                        // Reglas por método y ruta
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/notes/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/notes/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

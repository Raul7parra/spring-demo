package es.fplumara.dam2.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            org.springframework.core.convert.converter.Converter<org.springframework.security.oauth2.jwt.Jwt,
                    org.springframework.security.authentication.AbstractAuthenticationToken> jwtAuthConverter
    ) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello", "/info", "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/notes/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/notes/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                )
                .build();
    }
}

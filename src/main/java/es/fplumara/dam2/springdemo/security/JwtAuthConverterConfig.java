package es.fplumara.dam2.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class JwtAuthConverterConfig {

    @Bean
    Converter<Jwt, AbstractAuthenticationToken> jwtAuthConverter() {
        return jwt -> {
            List<String> roles = jwt.getClaimAsStringList("roles");
            var authorities = roles == null ? List.<SimpleGrantedAuthority>of() :
                    roles.stream()
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                            .collect(Collectors.toList());

            return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
        };
    }
}

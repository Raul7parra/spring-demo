package es.fplumara.dam2.springdemo.controllers;

import es.fplumara.dam2.springdemo.config.JwtProperties;
import es.fplumara.dam2.springdemo.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtEncoder jwtEncoder;
    private final JwtProperties props;

    public AuthController(AuthenticationManager authManager, JwtEncoder jwtEncoder, JwtProperties props) {
        this.authManager = authManager;
        this.jwtEncoder = jwtEncoder;
        this.props = props;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        var auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        var roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .map(a -> a.substring("ROLE_".length()))
                .collect(Collectors.toList());

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getTtlSeconds());

        var claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(exp)
                .subject(auth.getName())
                .claim("roles", roles)
                .build();

        var headers = org.springframework.security.oauth2.jwt.JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        var params = JwtEncoderParameters.from(headers, claims);
        String token = jwtEncoder.encode(params).getTokenValue();


        return ResponseEntity.ok(Map.of(
                "tokenType", "Bearer",
                "accessToken", token,
                "expiresAt", exp.toString()
        ));
    }
}

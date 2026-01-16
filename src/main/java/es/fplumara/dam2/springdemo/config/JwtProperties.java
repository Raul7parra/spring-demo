package es.fplumara.dam2.springdemo.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    @NotBlank
    @Size(min = 32)
    private String secret;

    @Min(60)
    private long ttlSeconds = 3600;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    public long getTtlSeconds() { return ttlSeconds; }
    public void setTtlSeconds(long ttlSeconds) { this.ttlSeconds = ttlSeconds; }
}

package es.fplumara.dam2.springdemo.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    @NotBlank
    private String url;

    @Min(100)
    private int timeoutMs;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public int getTimeoutMs() { return timeoutMs; }
    public void setTimeoutMs(int timeoutMs) { this.timeoutMs = timeoutMs; }
}

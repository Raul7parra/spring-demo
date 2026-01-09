package es.fplumara.dam2.springdemo.services;

import es.fplumara.dam2.springdemo.config.ApiProperties;
import org.springframework.stereotype.Service;

@Service
public class ApiClientService {

    private final ApiProperties props;

    public ApiClientService(ApiProperties props) {
        this.props = props;
    }

    public String summary() {
        return "URL=" + props.getUrl() + ", timeoutMs=" + props.getTimeoutMs();
    }
}

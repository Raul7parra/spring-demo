package es.fplumara.dam2.springdemo.config;

import es.fplumara.dam2.springdemo.domain.AppInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public AppInfo devInfo() {
        return new AppInfo("App DEV", "0.1");
    }

    @Bean
    @Profile("prod")
    public AppInfo prodInfo() {
        return new AppInfo("App PROD", "1.0");
    }

    @Bean
    @Profile("test")
    public AppInfo testInfo() {
        return new AppInfo("App TEST", "0.0");
    }

    /**
     @Bean public AppInfo defaultInfo() {
     return new AppInfo("App", "1.0");
     }
     **/

}

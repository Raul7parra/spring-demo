package es.fplumara.dam2.springdemo.controllers;

import es.fplumara.dam2.springdemo.domain.AppInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final AppInfo appInfo;

    public InfoController(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @GetMapping("/info")
    public AppInfo info() {
        return appInfo;
    }
}

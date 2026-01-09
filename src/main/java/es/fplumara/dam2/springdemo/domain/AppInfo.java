package es.fplumara.dam2.springdemo.domain;

public class AppInfo {

    private final String appName;
    private final String version;

    public AppInfo(String appName, String version) {
        this.appName = appName;
        this.version = version;
    }

    public String getAppName() { return appName; }
    public String getVersion() { return version; }
}
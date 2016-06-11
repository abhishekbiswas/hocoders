package com.barclaycardus.hackathon.hocoders.service.dto;

/**
 * Created by abhishek on 11/06/16.
 */
public class CoreRdsDbConfig {

    private String url;
    private String driver;
    private String user;
    private String password;

    public CoreRdsDbConfig(String url, String driver, String user, String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

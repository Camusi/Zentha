package com.zentha.zentha.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvLogger {

    @Value("${spring.datasource.url:undefined}")
    private String url;

    @Value("${spring.datasource.username:undefined}")
    private String user;

    @Value("${spring.datasource.password:undefined}")
    private String pass;

    @PostConstruct
    public void log() {
        System.out.println("DB URL: " + url);
        System.out.println("DB USER: " + user);
        System.out.println("DB PASS: " + pass);
    }
}


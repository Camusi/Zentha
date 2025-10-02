package com.zentha.zentha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZenthaApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ZenthaApplication.class);
        app.run(args);
    }
}

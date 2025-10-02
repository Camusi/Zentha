package com.zentha.zentha;

import com.zentha.zentha.config.EnvPropertiesInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZenthaApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ZenthaApplication.class);
        app.addInitializers(new EnvPropertiesInitializer());
        app.run(args);
    }
}

package com.zentha.zentha.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class EnvPropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        // Load env.properties from the current working directory
        Dotenv dotenv = Dotenv.configure()
                .filename("env.properties")
                .ignoreIfMissing()
                .load();

        // Put all env properties into Spring Environment
        Map<String, Object> props = new HashMap<>();
        dotenv.entries().forEach(entry -> props.put(entry.getKey(), entry.getValue()));

        // Ensure the spring profile is set from LOCATION
        if (props.containsKey("LOCATION")) {
            props.put("spring.profiles.active", props.get("LOCATION"));
        }

        // Add to Spring Environment as highest priority
        context.getEnvironment().getPropertySources()
                .addFirst(new MapPropertySource("dotenvProps", props));
    }
}

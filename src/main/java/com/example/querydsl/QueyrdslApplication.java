package com.example.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class QueyrdslApplication {
    public static void main(String[] args) {
        setProfile();
        SpringApplication.run(QueyrdslApplication.class, args);
    }

    private static void setProfile() {
        final String property = System.getProperty("spring.profiles.active");
        System.setProperty("spring.profiles.active", StringUtils.isEmpty(property) ? "h2" : property);
    }
}

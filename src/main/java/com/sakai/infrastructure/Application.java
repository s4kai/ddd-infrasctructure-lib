package com.sakai.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sakai")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
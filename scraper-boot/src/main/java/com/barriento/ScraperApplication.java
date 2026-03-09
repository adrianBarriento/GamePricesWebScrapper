package com.barriento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.barriento")
public class ScraperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScraperApplication.class, args);
    }
}

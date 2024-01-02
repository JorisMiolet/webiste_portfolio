package com.mediamarkt.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@EntityScan(basePackages = "com.mediamarkt.backend.models")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackendApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}

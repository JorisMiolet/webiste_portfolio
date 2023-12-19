package com.mediamarkt.backend;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.models.Laptop;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.ImageRepository;
import com.mediamarkt.backend.repositories.LaptopRepository;
import com.mediamarkt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.mediamarkt.backend.models")
public class BackendApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}

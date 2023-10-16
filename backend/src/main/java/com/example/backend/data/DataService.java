package com.example.backend.data;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class DataService {
    public List<ConcreteUserData> readUserDataFromFile() throws IOException {
        Gson gson = new Gson();
        ClassPathResource classPathResource = new ClassPathResource("data/loginData.json");
        try (InputStreamReader reader = new InputStreamReader(classPathResource.getInputStream())) {
            UserData userData = gson.fromJson(reader, UserData.class);

            return userData.getUsers();
        }
    }
    public LaptopData readLaptopDataFromFile() throws IOException {
        Gson gson = new Gson();
        ClassPathResource classPathResource = new ClassPathResource("data/dummyData.json");

        try (InputStreamReader reader = new InputStreamReader(classPathResource.getInputStream())) {
            return gson.fromJson(reader, LaptopData.class);
        }
    }
}



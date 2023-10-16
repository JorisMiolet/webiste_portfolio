package com.example.backend.controllers;


import com.example.backend.data.ConcreteLaptopData;
import com.example.backend.data.ConcreteUserData;
import com.example.backend.data.DataService;
import com.example.backend.data.LaptopData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
    @GetMapping("/getUserList")
    public List<ConcreteUserData> getUserData() throws IOException {
        return dataService.readUserDataFromFile();
    }
    @GetMapping("/getLaptopList")
    public List<ConcreteLaptopData> getLaptopData() throws IOException {
        return dataService.readLaptopDataFromFile().getLaptops();
    }
}

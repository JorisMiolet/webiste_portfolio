package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Laptop;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    @PostMapping("/importLaptops")
    public ResponseEntity<String> importLaptops(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            List<Laptop> laptops = new ArrayList<>();

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String ean = parts[0].trim();
                    String barcode = parts[1].trim();
                    String brand = parts[2].trim();
                    String description = parts[3].trim();
                    Laptop laptop = new Laptop(ean, barcode, brand, description);
                    laptops.add(laptop);
                }
            }
            laptopRepository.addLaptops(laptops);

            return new ResponseEntity<>("Laptops imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error importing laptops: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAll")
    public List<Laptop> getLaptops(){
        List<Laptop> laptops = laptopRepository.getAllLaptops();
        if(laptops == null){
            throw new ResourceNotFoundException("Er zijn geen laptops gevonden");
        }

        return laptops;
    }
    @PostMapping
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop){
        Laptop createdLaptop = laptopRepository.saveLaptop(laptop);

        return ResponseEntity.ok(createdLaptop);
    }

}

package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Laptop;
import com.mediamarkt.backend.repositories.LaptopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Laptop>> getAllLaptops() {
        List<Laptop> laptops = laptopRepository.getAll();
        return new ResponseEntity<>(laptops, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable Long id) {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop != null) {
            return new ResponseEntity<>(laptop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
                    Laptop laptop = new Laptop(null, ean, barcode, brand, description);
                    laptops.add(laptop);
                }
            }
            laptopRepository.addLaptops(laptops);

            return new ResponseEntity<>("Laptops imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error importing laptops: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @PostMapping("/create-laptop")
     @Transactional()
     public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop newLaptop) {
        Laptop createdLaptop = laptopRepository.create(newLaptop);
        return new ResponseEntity<>(createdLaptop, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable Long id, @RequestBody Laptop updatedLaptop) {
        Laptop existingLaptop = laptopRepository.findById(id);
        if (existingLaptop != null) {
            updatedLaptop.setId(id);
            Laptop mergedLaptop = laptopRepository.updateLaptop(updatedLaptop);
            return new ResponseEntity<>(mergedLaptop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Laptop> deleteLaptop(@PathVariable Long id) {
        Laptop deletedLaptop = laptopRepository.deleteLaptop(id);
        if (deletedLaptop != null) {
            return new ResponseEntity<>(deletedLaptop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.image;
import com.mediamarkt.backend.repositories.imageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class imageController {

    @Autowired
    private imageRepository imagesRepository;

    @GetMapping("/all")
    public List<image> getAllImages(){
        return imagesRepository.getAll();
    }

    @GetMapping("{articleNr}")
    public image getImageById(@PathVariable String articleNr){
        return imagesRepository.findById(articleNr);
    }
}

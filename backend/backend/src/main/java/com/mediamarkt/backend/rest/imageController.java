package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.image;
import com.mediamarkt.backend.repositories.imageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @PostMapping("/create-image")
    public image createImage(@RequestBody image newImage){
        return imagesRepository.create(newImage);
    }

    @PutMapping("{articleNr}")
    public image updateImageById(@PathVariable String articleNr, @RequestBody image newImage){
        if(!articleNr.equals(newImage.articleNumber)){
            throw new RuntimeException("article numbers do not match");
        }

        return imagesRepository.updateImage(newImage);
    }
}

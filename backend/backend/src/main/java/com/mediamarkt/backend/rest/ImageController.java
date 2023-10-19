package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imagesRepository;

    @GetMapping("/all")
    public List<Image> getAllImages(){
        return imagesRepository.getAll();
    }

    @GetMapping("{articleNr}")
    public Image getImageById(@PathVariable String articleNr){
        return imagesRepository.findById(articleNr);
    }

    @PostMapping("/create-image")
    public Image createImage(@RequestBody Image newImage){
        return imagesRepository.create(newImage);
    }

    @PutMapping("{articleNr}")
    public Image updateImageById(@PathVariable String articleNr, @RequestBody Image newImage){
        if(!articleNr.equals(newImage.articleNumber)){
            throw new RuntimeException("article numbers do not match");
        }

        return imagesRepository.updateImage(newImage);
    }
}

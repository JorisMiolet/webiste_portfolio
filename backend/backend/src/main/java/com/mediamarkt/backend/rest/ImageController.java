package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.exceptions.PreConditionFailedException;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imagesRepository;

    @GetMapping("/all")
    public List<Image> getAllImages(){
        List<Image> images = imagesRepository.getAll();
        if(images == null){
            throw new ResourceNotFoundException("Er zijn geen images gevonden");
        }
        return images;
    }

    @GetMapping("{articleNr}")
    public Image getImageById(@PathVariable String articleNr){
        Image image = imagesRepository.findById(articleNr);
        if(image == null){
            throw new ResourceNotFoundException("Er is geen image met articleNr: " + articleNr + " gevonden");
        }
        return image;
    }

    @PostMapping("/create-image")
    public ResponseEntity<Image> createImage(@RequestBody Image newImage){
        Image createdImage = imagesRepository.create(newImage);

        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();

        return ResponseEntity.created(uriBuilder.build().toUri())
                .body(createdImage);
    }

    @PutMapping("{articleNr}")
    public Image updateImageById(@PathVariable String articleNr, @RequestBody Image newImage){
        if(!articleNr.equals(newImage.articleNumber)){
            throw new PreConditionFailedException("article nummers zijn geen match");
        }

        return imagesRepository.updateImage(newImage);
    }
}
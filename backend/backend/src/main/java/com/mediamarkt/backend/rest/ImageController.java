package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.exceptions.PreConditionFailedException;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imagesRepository;

    @GetMapping("/all")
    public List<Image> getAllImages() {
        List<Image> images = imagesRepository.getAll();
        System.out.println(images);
        System.out.println("^^ images queried ^");
        if (images == null) {
            throw new ResourceNotFoundException("Er zijn geen images gevonden");
        }
        return images;
    }

    @GetMapping("/barcode/{barcode}")
    public Image getByBarcode(@PathVariable String barcode) {
        Image image = imagesRepository.findByBarcode(barcode);
        if (image == null) {
            throw new ResourceNotFoundException("Er is geen image gelinkt aan deze barcode: " + barcode);
        }
        return image;
    }

    @GetMapping("{articleNr}")
    public Image getImageById(@PathVariable String articleNr) {
        Image image = imagesRepository.findByArticleNr(articleNr);
        if (image == null) {
            throw new ResourceNotFoundException("Er is geen image met articleNr: " + articleNr + " gevonden");
        }
        return image;
    }

    @PostMapping("/create-image")
    @Transactional
    public ResponseEntity<Image> createImage(@RequestBody Image newImage) {
        newImage.setDate(LocalDate.now().toString());

        // Persist the newImage in the database
        Image createdImage = imagesRepository.create(newImage);

        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();

        return ResponseEntity.created(uriBuilder.build().toUri())
                .body(createdImage);
    }

    @PutMapping("/edit/{articleNr}")
    @Transactional
    public ResponseEntity<Image> updateImageById(@PathVariable String articleNr, @RequestBody Image newImage) {
        if (!articleNr.equals(newImage.getArticleNumber())) {
            throw new PreConditionFailedException("article nummers zijn geen match");
        }

        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        return ResponseEntity.created(uriBuilder.build().toUri()).body(imagesRepository.updateImage(newImage));
    }

    @DeleteMapping("{articleNr}")
    @Transactional
    public void deleteImage(@PathVariable String articleNr) {
        Image image = imagesRepository.deleteImage(articleNr);

        if (image == null) {
            throw new ResourceNotFoundException("image with article number: " + articleNr + " not found");
        }
    }

    @GetMapping("/EAN/{EAN}")
    public Image GetImageByEAN(@PathVariable String EAN) {
        Image image = imagesRepository.findByEAN(EAN);
        if (image == null) {
            throw new ResourceNotFoundException("Er is geen image met EANnr: " + EAN + " gevonden");
        }
        return image;
    }

    @GetMapping("/search")
    public List<Image> searchImage(
            @RequestParam(required = false) String Filter
            ) {
        List<Image> images = imagesRepository.search(Filter);
        return images;
    }

    @GetMapping("/completed")
    public List<Image> getCompletedImages() {
        List<Image> images = imagesRepository.getCompletedImages();
        return images;
    }

    @GetMapping("/incomplete")
    public List<Image> getInCompleteImages() {
        List<Image> images = imagesRepository.getIncompletedImages();
        return images;
    }

    @GetMapping("/outdated")
    public List<Image> getOutdatedImages() {
        List<Image> images = imagesRepository.getOutdatedImages();
        return images;
    }

}

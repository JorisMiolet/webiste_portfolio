package com.mediamarkt.backend.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mediamarkt.backend.exceptions.PreConditionFailedException;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @DeleteMapping("{id}")
    @Transactional
    public Image deleteImage(@PathVariable Long id) {
        Image image = imagesRepository.deleteImage(id);

        if (image == null) {
            throw new ResourceNotFoundException("image with article number: " + id + " not found");
        }
        return image;
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

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        Map<String, Long> images = imagesRepository.getSummaryStatistics();
        return images;
    }

    @PostMapping("/pickup")
    @Transactional
    public Image pickup(@RequestBody ObjectNode articleNumber) {
        String articleNr = articleNumber.get("article_nr").asText();
        UUID user_id = UUID.fromString(articleNumber.get("user_id").asText());
        return imagesRepository.pickup(articleNr, user_id);
    }


    @PostMapping("/done")
    @Transactional
    public ResponseEntity<Image> markDone(@RequestBody ObjectNode articleNumber) {
        String articleNr = articleNumber.get("article_nr").asText();

        Image markedImage = imagesRepository.markDone(articleNr);

        if (markedImage != null) {
            return ResponseEntity.ok(markedImage);
        } else {
            throw new ResourceNotFoundException("Image not found or cannot be marked as done");
        }
    }

}

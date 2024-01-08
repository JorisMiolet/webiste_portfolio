package com.mediamarkt.backend;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ImageRepositoryTests {
    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void testRepoCreate() {
        Image newImage = new Image(null, "LT10", "1234567820123", "BrandX",  "High-performance laptop","Intel Core i7", "16GB",  "512GB SSD", "NVIDIA GeForce GTX 1650" , "16", "40", "15","2021-12-13 10:00:00","outdated");

        Image createdImage = imageRepository.create(newImage);
        System.out.println(createdImage);
        assertNotNull(createdImage);

        List<Image> images = imageRepository.getAll();

        assertTrue(images.contains(createdImage));
    }

    @Test
    public void testRepoGetAll() {
        List<Image> images = imageRepository.getAll();

        assertNotNull(images);
        assertFalse(images.isEmpty());
    }

    @Test
    public void testRepoUpdate() {
        Image image = new Image();
        image.setStatus("completed");
        Image savedImage = imageRepository.updateImage(image);

        assertNotNull(savedImage);
        assertEquals(savedImage.getStatus(), image.getStatus());
    }

    @Test
    public void testRepoDelete() {
        List<Image> images = imageRepository.getAll();
        Image image = images.get(0);
        Image deleteImage = imageRepository.deleteImage(image.getId());

        assertNotNull(deleteImage);
        assertEquals(image, deleteImage);
    }
}

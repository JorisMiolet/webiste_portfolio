package com.mediamarkt.backend;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.ImageRepository;
import com.mediamarkt.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@Transactional
public class ImageRepositoryTests {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetAllImages() {
        // Act: call the method
        List<Image> images = imageRepository.getAll();

        // Assert: check the result
        assertNotNull(images.toString(), images);
        assertTrue("The images array should not be empty", !images.isEmpty());
        assertEquals(4, images.size());
        assertTrue("All images should have non-null IDs", images.stream().allMatch(image -> image.getId() != null));
    }

    @Test
    public void testFindImageByBarcode() {
        // Arrange data
        String barcode = "1234567890123";

        // Act: call the method
        Image image = imageRepository.findByBarcode(barcode);

        // Assert: check the result
        assertNotNull(image.toString(), image);
        assertEquals(barcode, image.getBarcode());
    }

    @Test
    public void testIncorrectFindImageByBarcode(){
        // Arrange data
        String barcode = null;

        // Act: call the method
        Image image = imageRepository.findByBarcode(barcode);

        // Assert: check the result
        assertEquals(null, image);
    }


    @Test
    public void testCreateImage() {
        // Arrange data
        Image newImage = new Image();

        // Act: call the method
        Image createdImage = imageRepository.create(newImage);

        // Assert: check the result
        assertNotNull(createdImage.toString(), createdImage);
        assertNotNull("Found image should have a non-null ID", newImage.getId());
    }

    @Test
    public void testUpdateImage() {
        // Arrange data
        Image imageToUpdate = new Image();

        // Act: call the method
        Image updatedImage = imageRepository.updateImage(imageToUpdate);

        // Assert: check the result
        assertNotNull(updatedImage.toString(), updatedImage);
    }

    @Test
    public void testDeleteImage() {
        // Arrange data
        Long imageIdToDelete = 1L;

        // Act: call the method
        Image deletedImage = imageRepository.deleteImage(imageIdToDelete);

        // Assert: check the result
        assertNotNull(deletedImage.toString(), deletedImage);
    }


    @Test
    public void testGetCompletedImages() {
        // Arrange data
        List<Image> completedImages;

        // Act: call the method
        completedImages = imageRepository.getCompletedImages();

        // Assert: check the result
        assertNotNull(completedImages.toString(), completedImages);
    }
    @Test
    @Transactional
    public void testPickupImage() {
        // Arrange data
        Image newImage = new Image();
        newImage.setArticleNumber("LT1001");
        User newUser = new User();;

        imageRepository.create(newImage);
        userRepository.create(newUser);

        // call the method
        Image pickedUpImage = imageRepository.pickup("LT1001", userRepository.getUserByUUID(newUser.getUuid()).getId());

        // Assert: check the result
        assertNotNull(pickedUpImage.toString(), pickedUpImage);
        assertEquals("in progress", pickedUpImage.getStatus());
    }

    @Test
    @Transactional
    public void testMarkDoneImage(){
        // Arrange data
        Image newImage = new Image();
        newImage.setArticleNumber("LT1001");
        User newUser = new User();;
        imageRepository.create(newImage);
        userRepository.create(newUser);

        // call the method
        Image markedDoneImage = imageRepository.markDone("LT1001");

        // Assert: check the result
        assertNotNull(markedDoneImage.toString(), markedDoneImage);
        assertEquals("completed", markedDoneImage.getStatus());
        assertNotNull(markedDoneImage.getDate().toString(), markedDoneImage.getDate());
    }

}
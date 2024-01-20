package com.mediamarkt.backend;

import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testCreate() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("testPassword");

        // Act
        User createdUser = userRepository.create(newUser);

        // Assert
        assertNotNull(createdUser, "created user should not be null");
        assertEquals(newUser.getUsername(), createdUser.getUsername(), "new user username and created username should be equal");
    }


    @Test
    public void testUpdateUser() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("initialUsername");
        newUser.setPassword("initialPassword");

        // Save the user to the database
        User existingUser = userRepository.create(newUser);

        // Act: Make changes to the user data and update
        User updatedUser = userRepository.getUserByUUID(existingUser.getUuid());
        updatedUser.setUsername("updatedUsername");

        // Update the user
        User result = userRepository.updateUser(updatedUser);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(updatedUser.getUsername(), result.getUsername(), "result username and updated user username should be equal");
    }


    @Test
    public void testDeleteUser() {
        // Arrange
        User userToDelete = new User();
        userToDelete.setUsername("userToDelete");
        userToDelete.setPassword("passwordToDelete");

        // Create and persist the user
        User createdUser = userRepository.create(userToDelete);

        // Act: Delete the user
        User deletedUser = userRepository.deleteUser(createdUser.getUuid());

        // Assert
        assertNotNull(deletedUser);
        assertNull(userRepository.getUserByUUID(createdUser.getUuid()));
    }

}

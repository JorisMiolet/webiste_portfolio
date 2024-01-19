package com.mediamarkt.backend;

import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.rest.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Test
    public void testGetAll(){
        // Act
        List<User> users = userController.getAllUsers();

        // Assert
        assertNotNull(users, "list of users should not be null");
        assertFalse(users.isEmpty(), "the list should not be empty");
    }

    @Test
    public void testCreate(){
        //Arrange
        User newUser = new User();
        newUser.setUsername("userToCreate");
        newUser.setPassword("passwordToCreate");

        //Act
        User createdUser = userController.createUser(newUser).getBody();

        //Assert
        assertNotNull(createdUser, "user should not be null");
        assertEquals("userToCreate", createdUser.getUsername());
    }
}

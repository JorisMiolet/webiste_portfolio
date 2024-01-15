package com.mediamarkt.backend;

import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.rest.UserController;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserControllerTests {

    @Autowired
    BackendApplication application = null;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        assertNotNull(application);
        System.out.println("Application auto-configuration has succeeded");
    }

    @Test
    void getAllUsers(){
        List<User> users = userController.getAllUsers();
        assertNotNull(users);
        System.out.println(users);
    }

    @Test
    void getActiveUsers(){
        List<User> users = userController.getActiveUsers();
        assertNotNull(users);
        System.out.println(users);
    }
}

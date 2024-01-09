package com.mediamarkt.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    BackendApplication application = null;
    @Test
    void contextLoads() {
        assertNotNull(application);
        System.out.println("Application auto-configuration has succeeded");
    }

}

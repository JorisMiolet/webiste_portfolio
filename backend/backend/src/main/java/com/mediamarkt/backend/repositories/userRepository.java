package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.image;
import com.mediamarkt.backend.models.user;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class userRepository {

    private List<user> users;

    public userRepository(){
        users = new ArrayList<>();

        users.add(new user("John", "test123", true));
        users.add(new user("Tessa", "test456", false));
        users.add(new user("Tim", "nogEenTest", false));
        users.add(new user("Jeffry", "enNogEenTest", true));
        users.add(new user("Pieter", "laatsteTest", false));
    }

    public List<user> getAll() {
        return users;
    }

    public user getUserByUUID(UUID uuid) {
        for(user foundUser: users){
            if (foundUser.getUuid().equals(uuid)) {
                return foundUser;
            }
        }
        return null;
    }
}

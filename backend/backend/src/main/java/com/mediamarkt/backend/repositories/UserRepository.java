package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserRepository {

    private final List<User> users;

    public UserRepository(){
        users = new ArrayList<>();

        users.add(new User("John", "test123", true));
        users.add(new User("Tessa", "test456", false));
        users.add(new User("Tim", "nogEenTest", false));
        users.add(new User("Jeffry", "enNogEenTest", true));
        users.add(new User("Pieter", "laatsteTest", false));
    }

    public List<User> getAll() {
        return users;
    }

    public User getUserByUUID(UUID uuid) {
        for(User foundUser: users){
            if (foundUser.uuid.equals(uuid)) {
                return foundUser;
            }
        }
        return null;
    }

    public User create(User newUser) {
        users.add(newUser);
        return newUser;
    }

    public User updateUser(User newUser, UUID uuid) {
        for(User foundUser : users){
            if(foundUser.uuid.equals(uuid)){
                int index = users.indexOf(foundUser);
                users.set(index, newUser);
                return newUser;
            }
        }
        return null;
    }
}

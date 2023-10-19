package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return usersRepository.getAll();
    }

    @GetMapping("{uuid}")
    public User getUserByUUID(@PathVariable UUID uuid){
        return usersRepository.getUserByUUID(uuid);
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User newUser){
        return usersRepository.create(newUser);
    }

    @PutMapping("{uuid}")
    public User updateImageById(@PathVariable UUID uuid, @RequestBody User newUser){
        return usersRepository.updateUser(newUser, uuid);
    }
}

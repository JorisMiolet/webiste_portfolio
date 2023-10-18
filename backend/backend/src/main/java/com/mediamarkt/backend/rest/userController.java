package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.image;
import com.mediamarkt.backend.models.user;
import com.mediamarkt.backend.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private userRepository usersRepository;

    @GetMapping("/all")
    public List<user> getAllUsers(){
        return usersRepository.getAll();
    }

    @GetMapping("{uuid}")
    public user getUserByUUID(@PathVariable UUID uuid){
        return usersRepository.getUserByUUID(uuid);
    }

    @PostMapping("/create-user")
    public user createUser(@RequestBody user newUser){
        return usersRepository.create(newUser);
    }

    @PutMapping("{uuid}")
    public user updateImageById(@PathVariable UUID uuid, @RequestBody user newUser){
        return usersRepository.updateUser(newUser, uuid);
    }
}

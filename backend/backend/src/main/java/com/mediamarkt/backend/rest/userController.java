package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.models.image;
import com.mediamarkt.backend.models.user;
import com.mediamarkt.backend.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}

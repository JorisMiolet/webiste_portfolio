package com.mediamarkt.backend.rest;

import com.mediamarkt.backend.exceptions.PreConditionFailedException;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.Laptop;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = usersRepository.getAll();
        System.out.println(users);
        if (users == null) {
            throw new ResourceNotFoundException("Er zijn geen users gevonden");
        }
        return users;
    }

//    @PostMapping("/login")
//    public User getUserWithLogin(@RequestBody User credentials){
//       String username = credentials.getUsername();
//       String password = credentials.getPassword();
//        User user = usersRepository.getWithLogin(username, password);
//        if(user == null){
//            throw new ResourceNotFoundException("Er zijn geen users gevonden");
//        }
//        return user;
//    }

    @GetMapping("{uuid}")
    public User getUserByUUID(@PathVariable UUID uuid) {
        User user = usersRepository.getUserByUUID(uuid);
        if (user == null) {
            throw new ResourceNotFoundException("Er is geen user met uuid: " + uuid + " gevonden");
        }
        return user;
    }

    @PostMapping("/create-user")
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser = usersRepository.create(newUser);

        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();

        return ResponseEntity.created(uriBuilder.build().toUri())
                .body(createdUser);
    }

    @PutMapping("{uuid}")
    public User updateUserByUUID(@PathVariable UUID uuid, @RequestBody User newUser) {
        if (!uuid.equals(newUser.getUuid())) {
            throw new PreConditionFailedException("uuid's zijn geen match");
        }
        return usersRepository.updateUser(newUser);
    }

    @DeleteMapping("{uuid}")
    public void deleteUserByUUID(@PathVariable UUID uuid) {
        if (usersRepository.getUserByUUID(uuid) == null) {
            throw new ResourceNotFoundException("er is geen user met dit UUID");
        }
        usersRepository.deleteUser(uuid);
    }

    @GetMapping("/search")
    public List<User> searchImage(@RequestParam(required = false) String Filter) {
        return usersRepository.search(Filter);
    }
}

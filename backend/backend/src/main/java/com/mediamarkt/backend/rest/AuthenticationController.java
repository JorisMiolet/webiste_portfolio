package com.mediamarkt.backend.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mediamarkt.backend.Token.JWToken;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    UserRepository usersRepository;
    JWToken jwToken = new JWToken();

    @PostMapping("/login")
    public ResponseEntity<User> authenticateAccount(@RequestBody ObjectNode signInInfo) {
        String username = signInInfo.get("username").toString().replaceAll("^\"|\"$", "");
        String password = signInInfo.get("password").toString().replaceAll("^\"|\"$", "");
        User user = usersRepository.getWithLogin(username, password);
        if (user == null) {
            throw new ResourceNotFoundException("Er zijn geen users gevonden");
        }

        String tokenString = jwToken.encode(user.isAdmin(), user.getId());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }
}

package com.mediamarkt.backend.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mediamarkt.backend.Token.JWToken;
import com.mediamarkt.backend.exceptions.ResourceNotFoundException;
import com.mediamarkt.backend.models.User;
import com.mediamarkt.backend.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    UserRepository usersRepository;
    JWToken jwToken = new JWToken();

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAccount(@RequestBody ObjectNode signInInfo) {
        String email = signInInfo.get("email").asText();
        String password = signInInfo.get("password").asText();

        User user = usersRepository.getByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email niet gevonden");
        } else {
            // Vergelijk het ingevoerde wachtwoord met het opgeslagen gehashte wachtwoord
            if (BCrypt.checkpw(password, user.getPassword())) {
                // Wachtwoorden komen overeen, genereer JWT-token en retourneer gebruiker
                String tokenString = jwToken.encode(user.isAdmin(), user.getId());

                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                        .body(user);
            } else {
                // Wachtwoorden komen niet overeen
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Onjuist wachtwoord");
            }
        }
    }


}

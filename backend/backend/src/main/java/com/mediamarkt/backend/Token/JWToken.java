package com.mediamarkt.backend.Token;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
public class JWToken {
    private static final String JWT_ADMIN_CLAIM = "sub";
    private static final String JWT_ACCOUNTID_CLAIM = "id";
    boolean isAdmin;
    UUID accountId;
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.pass-phrase}")
    private String passphrase;

    @Value("${jwt.expiration-seconds}")
    private int expiration;


    public JWToken(boolean isAdmin, UUID accountId) {
        this.accountId = accountId;
        this.isAdmin = isAdmin;
    }

    public String encode() {
        System.out.println(passphrase + issuer + expiration);
        Key key = getKey(passphrase);
        return Jwts.builder()
                .claim(JWT_ADMIN_CLAIM, this.isAdmin)
                .claim(JWT_ACCOUNTID_CLAIM, this.accountId)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }
}
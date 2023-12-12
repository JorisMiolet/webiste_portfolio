package com.mediamarkt.backend.Token;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.AuthenticationException;

@Component
public class JWToken {
    private static final String JWT_ADMIN_CLAIM = "admin";
    private static final String JWT_ACCOUNTID_CLAIM = "id";

    private final String issuer = "hva";


    private final String passphrase = "sjhfsaKJHASKajhkfsajhfkashfskajKAJFHsKJAOAWIUFREAJKDPOQWOPROIPQWRPORWQPIOksfalkhfsalkfsa";

    private final int expiration = 3600;
    private Claims claims;

    public String encode(boolean isAdmin, UUID accountId) {
        Key key = getKey(passphrase);
        return Jwts.builder()
                .claim(JWT_ADMIN_CLAIM, Boolean.toString(isAdmin))
                .claim(JWT_ACCOUNTID_CLAIM, accountId)
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

    public JWToken decode(String encodedToken) throws AuthenticationException {
        JWToken jwt = new JWToken();
        try {
            // Validate the token
            Key key = getKey(passphrase);

            Jws<Claims> jws = Jwts.parserBuilder().
                    setSigningKey(passphrase.getBytes()).
                    build().
                    parseClaimsJws(encodedToken);

            Claims claims = jws.getBody();
            System.out.println(claims);
            jwt.setClaims(claims);
            System.out.println(jwt.claims);
            return jwt;
        } catch (MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException | SignatureException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (ExpiredJwtException e) {
            jwt.setClaims(e.getClaims());
            return jwt;
        }
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public Claims getClaims() {
        return claims;
    }

    public String getPassPhrase() {
        return this.passphrase;
    }


}
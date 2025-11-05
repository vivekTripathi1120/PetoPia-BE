package com.petopia.petopia.utils;

import com.petopia.petopia.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class AuthUtils {

    @Value("${secret.key}")
    private String authKey;

    private SecretKey getSecretkey(){
        return Keys.hmacShaKeyFor(authKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(User user){

        return Jwts.builder()
                .subject(user.toString())
                .issuedAt(new Timestamp(System.currentTimeMillis()))
                .expiration(new Timestamp(System.currentTimeMillis() + 1000 * 60 * 30)) // 10 min expiry
                .signWith(getSecretkey())
                .compact();
    }
}

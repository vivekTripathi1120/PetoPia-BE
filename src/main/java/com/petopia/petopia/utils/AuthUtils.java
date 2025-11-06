package com.petopia.petopia.utils;

import com.petopia.petopia.Dto.UserCacheDTO;
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
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthUtils {

    @Value("${secret.key}")
    private String authKey;

    @Value("${jwtExpiration}")
    private long jwtExpiryTime;

    private SecretKey getSecretkey(){
        return Keys.hmacShaKeyFor(authKey.getBytes(StandardCharsets.UTF_8));
    }

    private Long getExpiry(){
        return jwtExpiryTime;
    }

//    Generating JWT token
    public String generateJwtToken(User user){

        Map<String,String> claims = new HashMap<>();
        claims.put("email",user.getEmail());
//        claims.put("phoneNumber",phoneNumber);
        claims.put("roleId",user.getRole().getId().toString());
        claims.put("roleName",user.getRole().getRoleName());
        claims.put("userName",user.getUsername());
        claims.put("userId",user.getUserId().toString());

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("user",claims)
                .issuedAt(new Timestamp(System.currentTimeMillis()))
                .expiration(new Timestamp(System.currentTimeMillis() + getExpiry())) // 30 min expiry
                .signWith(getSecretkey())
                .compact();
    }


//    Authenticating Token
//
//    public UserCacheDTO validateToken(String jwtToken){
//        UserCacheDTO userCacheDTO = extractUserDetails(jwtToken);
//    }
//
//    private UserCacheDTO extractUserDetails(String jwtToken) {
//
//    }

    public Claims getUserNameFromToken(String token) {

        return Jwts.parser().verifyWith(getSecretkey()).build().parseSignedClaims(token).getPayload();
    }
}

package com.petopia.petopia.utils;

import com.petopia.petopia.cache.UserCacheDTO;
import com.petopia.petopia.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AuthUtils {

    @Value("${secret.key}")
    private String authKey;

    @Value("${jwtExpiration}")
    private long jwtExpiryTime;

    Logger logger = LoggerFactory.getLogger(this.getClass());

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


//    Authenticating Token data extraction
    public UserCacheDTO extractUserDetails(HttpServletRequest httpRequest) {

        String jwtToken = httpRequest.getHeader("Authorization").split("Bearer ")[1];
        logger.info("jwtToken..{}",jwtToken);
        Claims claims = Jwts.parser().verifyWith(getSecretkey()).build().parseSignedClaims(jwtToken).getPayload();

        UserCacheDTO userCacheDTO = new UserCacheDTO();
        if(null != claims){
            Map<String,String> extractedClaims = (Map<String, String>) claims.get("user");
            logger.info("Extracted Claims: {}",extractedClaims);
            if(null != extractedClaims){
                userCacheDTO.setUserName(extractedClaims.get("userName"));
                userCacheDTO.setEmail(extractedClaims.get("email"));
                userCacheDTO.setRoleId(Long.valueOf(extractedClaims.get("roleId")));
                userCacheDTO.setUserId(Long.valueOf(extractedClaims.get("userId")));
            }
        }

        return userCacheDTO;

    }

}

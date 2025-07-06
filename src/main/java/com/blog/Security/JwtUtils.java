package com.blog.Security;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${blog.app.jwtSecret:SecretKeyJwt}")
    private String jwtSecret;

    @Value("${blog.app.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    //  TOKEN ÜRET
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //  TOKEN'DAN USERNAME AL
    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //  TOKEN DOĞRULA
    public boolean validateJwt(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

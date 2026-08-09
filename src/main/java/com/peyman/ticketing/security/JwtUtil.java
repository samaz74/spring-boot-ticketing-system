package com.peyman.ticketing.security;

import com.peyman.ticketing.model.User;
import com.peyman.ticketing.repository.InvalidatedTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil{

    @Value("${jwt.secret}")
    private String seckretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(seckretKey.getBytes());
    }

    public String generateToken(User user){
        return Jwts.builder().subject(user.getUsername()).claim("ROLE_", user.getRole()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+ expiration)).signWith(getSigningKey()).compact();
    }
    public String extractUsername(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public Boolean isTokenValid(String token){
        try{
             Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
             return true;
        }catch (Exception e){
            return false;
        }
    }
    public LocalDateTime extractExpiration(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

}

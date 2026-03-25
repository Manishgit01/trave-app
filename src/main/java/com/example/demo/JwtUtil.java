package com.example.demo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component //"Create an object of this class automatically"
public class JwtUtil // Utility class
{
	// Used to sign the token
	// Secret password used to verify token authenticity
    private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // min 32 chars
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour
    
    // Converts your string secret into a secure cryptographic key
    private Key getKey() 
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // This method creates JWT
    public String generateToken(String username) 
    {
        return Jwts.builder()
                .setSubject(username) //Who is the user → "admin"
                .setIssuedAt(new Date()) //When token was created
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) //Token expiry time
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact(); //Converts everything into string
    }

    // Reads username from token
    public String extractUsername(String token) 
    {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token) // Decodes token
                .getBody() // "admin"
                .getSubject(); // "admin"
    }

    // Checks if token is valid or not
    public boolean isValid(String token) 
    {
        try // Signature correct, token not  expired or modified
        {
        	// token valid - no error, invalid - exception
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
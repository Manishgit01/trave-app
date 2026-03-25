package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //"This class handles HTTP requests and returns JSON/text responses"

@RequestMapping("/auth") //All APIs in this class will start with /auth

//Take username + password → verify → generate JWT
public class AuthController //LOGIN API (GENERATE TOKEN)
{

    @Autowired
    private JwtUtil jwtUtil; //Injects your JwtUtil class

    // Hardcoded user (for now)
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) 
    {
    	//a simple hardcoded check
        if ("admin".equals(username) && "1234".equals(password)) {
            //Create JWT token using username
        	return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Invalid credentials");
    }
    
}
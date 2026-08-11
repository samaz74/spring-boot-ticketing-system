package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.AuthResponse;
import com.peyman.ticketing.dto.LoginRequest;
import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public AuthResponse register(@RequestBody UserRequest userRequest){
        return authService.registerUser(userRequest);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest.getUsername(),loginRequest.getPassword());
    }
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authHeader){
        String token =authHeader.substring(7);
        authService.logout(token);
    }
}

package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.AuthResponse;
import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.mapper.UserMapper;
import com.peyman.ticketing.exeption.DuplicateResourceException;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.InvalidatedToken;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.model.enums.Roles;
import com.peyman.ticketing.repository.InvalidatedTokenRepository;
import com.peyman.ticketing.repository.UserRepository;
import com.peyman.ticketing.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, InvalidatedTokenRepository invalidatedTokenRepository){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    public AuthResponse registerUser(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new DuplicateResourceException("کاربری تکراری است.");
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("ایمیل تکراری است.");
        }else {
            User user = UserMapper.toEntity(userRequest);
            user.setRole(Roles.USER);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(user);
            return new AuthResponse(null ,user.getUsername(),user.getRole());
        }

    }
    public AuthResponse login (String username, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("نام کاربری اشتباه است."));
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token,username,user.getRole());

    }
    public void logout(String token){
        String username=jwtUtil.extractUsername(token);
        LocalDateTime expireAt = jwtUtil.extractExpiration(token);
        InvalidatedToken invalidatedToken = new InvalidatedToken(token,username,expireAt);
        invalidatedTokenRepository.save(invalidatedToken);
    }
}

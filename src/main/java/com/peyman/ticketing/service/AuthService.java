package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.AuthResponse;
import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.mapper.UserMapper;
import com.peyman.ticketing.exeption.DuplicateResourceException;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.repository.UserRepository;
import com.peyman.ticketing.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    public AuthResponse registerUser(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new DuplicateResourceException("کاربری تکراری است.");
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("ایمیل تکراری است.");
        }else {
            User user = UserMapper.toEntity(userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(user);
            return new AuthResponse(null ,user.getUsername(),user.getRole());
        }

    }

}

package com.peyman.ticketing.service;

import com.peyman.ticketing.exeption.DuplicateResourceException;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.model.enums.Roles;
import com.peyman.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateResourceException("نام کاربری تکراری است");
        } else if (userRepository.existsByEmail(user.getEmail())) {
                throw new DuplicateResourceException("پست الکترونیک تکراری است");
        } else return userRepository.save(user);
    }
    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void changeRole(Long id, Roles role) {
        User user = userRepository.findById(id).get();
        user.setRole(role);
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}


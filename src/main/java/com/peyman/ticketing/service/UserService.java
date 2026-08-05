package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.UserResponse;
import com.peyman.ticketing.dto.mapper.UserMapper;
import com.peyman.ticketing.exeption.DuplicateResourceException;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.model.enums.Roles;
import com.peyman.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponse register(UserRequest user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateResourceException("نام کاربری تکراری است");
        } else if (userRepository.existsByEmail(user.getEmail())) {
                throw new DuplicateResourceException("پست الکترونیک تکراری است");
        } else {
            User userModel = UserMapper.toEntity(user);
            userRepository.save(userModel);
            return UserMapper.userResponse(userModel);
        }
    }
    public User getEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("کاربر یافت نشد"));
    }
    public Optional<UserResponse> getById(Long id){
        return userRepository.findById(id).map(UserMapper::userResponse);
    }
    public List<UserResponse> getAll(){
        return userRepository.findAll().stream().map(UserMapper::userResponse).collect(Collectors.toUnmodifiableList());
    }
    public Optional<UserResponse> getByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::userResponse);
    }
    public void changeRole(Long id, Roles role) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("یوزر جستجو شده یافت نشد."));
        user.setRole(role);
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
        userRepository.deleteById(id);}
        else throw new ResourceNotFoundException("کاربر یافت نشد");
    }
}


package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.UserResponse;
import com.peyman.ticketing.model.User;

public class UserMapper {
    public static UserResponse userResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getCreateDate()
        );
    }
    public static User toEntity(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        return user;
    }
}

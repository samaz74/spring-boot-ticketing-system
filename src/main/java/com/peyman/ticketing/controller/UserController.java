package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.UserResponse;
import com.peyman.ticketing.model.enums.Roles;
import com.peyman.ticketing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }
    @GetMapping("/UserName/{userName}")
    public UserResponse getUserByUsername(@PathVariable String userName) {
        return userService.getByUsername(userName);
    }
    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }
    @PatchMapping("/{id}/role")
    public void updateRole(@PathVariable Long id, @RequestParam Roles role) {
        userService.changeRole(id, role);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

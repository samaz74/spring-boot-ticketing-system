package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.UserRequest;
import com.peyman.ticketing.dto.UserResponse;
import com.peyman.ticketing.model.enums.Roles;
import com.peyman.ticketing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }
    @GetMapping("/UserName/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public UserResponse getUserByUsername(@PathVariable String userName) {
        return userService.getByUsername(userName);
    }
    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }
    @PatchMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateRole(@PathVariable Long id, @RequestParam Roles role) {
        userService.changeRole(id, role);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

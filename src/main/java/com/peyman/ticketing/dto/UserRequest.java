package com.peyman.ticketing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "نام نمی تواند خالی باشد.")
    private String firstName;
    @NotBlank(message = "نام خانوادگی نمی تواند خالی باشد.")
    private String lastName;
    @NotBlank(message = "نام کاربری نمی تواند خالی باشد.")
    private String username;
    @NotBlank(message = "کلمه عبور نمی تواند خالی باشد.")
    private String password;
    @NotBlank(message = "ایمیل نمی تواند خالی باشد.")
    private String email;
    @NotBlank(message = "شماره تماس نمی تواند خالی باشد.")
    private String phone;
}

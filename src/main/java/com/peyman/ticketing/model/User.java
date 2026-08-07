package com.peyman.ticketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.peyman.ticketing.model.enums.Roles;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @NotBlank(message = "یوزر نمی تواند خالی باشد")
    private String username;
    @NotBlank(message = "پسورد نمی تواند خالی باشد")
    private String password;
    @NotBlank(message = "نام نمی تواند خالی باشد")
    private String firstName;
    @NotBlank(message = "نام خوانوادگی نمی تواند خالی باشد")
    private String lastName;
    @NotBlank(message = "ایمیل نمی تواند خالی باشد")
    @Column(unique=true)
    private String email;
    @NotBlank(message = " شماره تلفن نمی تواند خالی باشد")
    @Column(unique=true)
    private String phone;
    @CreationTimestamp
    private LocalDateTime createDate;
    @Enumerated(EnumType.STRING)
    private Roles role;

    public User(String username, String password, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}

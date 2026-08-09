package com.peyman.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvalidatedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String username;
    private LocalDateTime expiresAt;
    @CreationTimestamp
    private LocalDateTime createAt;

    public InvalidatedToken(String token, String username,LocalDateTime expiresAt){
        this.token=token;
        this.username=username;
        this.expiresAt=expiresAt;
    }
}

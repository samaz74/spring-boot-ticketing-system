package com.peyman.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubSystemResponse {
    private Long id;
    private String name;
    private String description;
    private String prefix;
    private Boolean isActive;
    private Long ticketingSystemId;
    private LocalDateTime createdAt;
}

package com.peyman.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketingSystemResponse {
    private Long id;
    private String name;
    private String apiKey;
    private Boolean isActive;
    private LocalDateTime createdAt;
}

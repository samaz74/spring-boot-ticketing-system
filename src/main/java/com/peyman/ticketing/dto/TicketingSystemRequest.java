package com.peyman.ticketing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketingSystemRequest {
    @NotBlank(message = "نام نمی تواند خالی باشد.")
    private String name;
}

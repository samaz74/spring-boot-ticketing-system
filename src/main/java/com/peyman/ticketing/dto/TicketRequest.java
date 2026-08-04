package com.peyman.ticketing.dto;

import com.peyman.ticketing.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotBlank(message = "عنوان نمی تواند خالی باشد.")
    private String title;
    @NotBlank(message = "پیام نمی تواند خالی باشد.")
    private String description;
    @NotNull(message = "اولویت نمی تواند نامعلوم باشد.")
    private Priority priority;
}

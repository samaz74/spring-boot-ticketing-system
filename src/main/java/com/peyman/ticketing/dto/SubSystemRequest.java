package com.peyman.ticketing.dto;

import com.peyman.ticketing.service.TicketingSystemService;
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
public class SubSystemRequest {

    @NotBlank(message = "نام نمی تواند خالی باشد.")
    private String name;
    private String description;
    @NotBlank(message = "کد شناسه سیستم نمی تواند خالی باشد.")
    private String prefix;
    @NotNull(message = "زیرسیستم حتما باید زیرمجموعه یک سیستم باشد.")
    private Long ticketingSystemId;
}

package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.TicketingSystemRequest;
import com.peyman.ticketing.dto.TicketingSystemResponse;
import com.peyman.ticketing.service.TicketingSystemService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/systems")
public class TicketingSystemController {
    private final TicketingSystemService ticketingSystemService;
    public TicketingSystemController(TicketingSystemService ticketingSystemService) {
        this.ticketingSystemService = ticketingSystemService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TicketingSystemResponse createTicketingSystem(@Valid @RequestBody TicketingSystemRequest ticketingSystemRequest) {
        return ticketingSystemService.create(ticketingSystemRequest);
    }

    @GetMapping
    public List<TicketingSystemResponse> getTicketingSystems() {
        return ticketingSystemService.getAll();
    }
    @GetMapping("/{id}")
    public TicketingSystemResponse getTicketingSystem(@PathVariable Long id) {
        return ticketingSystemService.getById(id);
    }
    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public void patchSystemStatus(@PathVariable Long id) {
        ticketingSystemService.toggleActive(id);
    }
}

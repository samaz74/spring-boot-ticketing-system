package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.TicketRequest;
import com.peyman.ticketing.dto.TicketResponse;
import com.peyman.ticketing.model.Ticket;
import com.peyman.ticketing.model.enums.TicketStatus;
import com.peyman.ticketing.service.TicketService;
import com.peyman.ticketing.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{userId}/{subSystemId}")
    public TicketResponse createTicket(@RequestBody TicketRequest ticket, @PathVariable Long userId, @PathVariable Long subSystemId ) {
        return ticketService.creatTicket(ticket,userId,subSystemId);
    }
    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getById(id);
    }
    @GetMapping("/user/{userId}")
    public List<TicketResponse> getTicketByUserId(@PathVariable Long userId) {
        return ticketService.getByUserId(userId);
    }
    @GetMapping("/subSystem/{subSystemId}")
    public List<TicketResponse> getTicketBySubSystem(@PathVariable Long subSystemId) {
        return ticketService.getBySUbSystem(subSystemId);
    }
    @GetMapping("/assigned/{userId}")
    public List<TicketResponse> getTicketByAssignedTo(@PathVariable Long userId) {
        return ticketService.getByAssignedTo(userId);
    }
    @GetMapping("/visible/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public List<TicketResponse> getTicketByVisible(@PathVariable Long userId) {
        return ticketService.getVisibleTickets(userId);
    }
    @PatchMapping("/{ticketId}/assign/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public void assignTicket(@PathVariable Long userId,@PathVariable Long ticketId) {
        ticketService.assignTicket(userId,ticketId);
    }
    @PatchMapping("/{ticketId}/status")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    public void changeTicketStatus(@PathVariable Long ticketId, @RequestParam TicketStatus status) {
        ticketService.changeStatus(ticketId,status);
    }
}

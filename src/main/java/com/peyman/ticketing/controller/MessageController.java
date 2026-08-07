package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.MessageRequest;
import com.peyman.ticketing.dto.MessageResponse;
import com.peyman.ticketing.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @PostMapping("/{ticketId}/{userId}")
    public MessageResponse sendMessage(@PathVariable Long ticketId, @PathVariable Long userId, @RequestBody MessageRequest message) {
        return messageService.addMessage(ticketId, userId, message);
    }

    @GetMapping("/{ticketId}")
    public List <MessageResponse> getMessage(@PathVariable Long ticketId) {
        return messageService.getMessagesByTicketId(ticketId);
    }

}

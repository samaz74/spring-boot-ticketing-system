package com.peyman.ticketing.service;

import com.peyman.ticketing.model.Message;
import com.peyman.ticketing.model.Ticket;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.repository.MessageRepository;
import com.peyman.ticketing.repository.TicketRepository;
import com.peyman.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private MessageService(MessageRepository messageRepository, TicketRepository ticketRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }
    public Message addMessage(Long ticketId, Long userId, String content) {
        Message message = new Message();
        User user =userService.getById(userId).get();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        message.setSentBy(user);
        message.setContent(content);
        message.setTicket(ticket);
        return messageRepository.save(message);
    }
    public List<Message> getMessagesByTicketId(Long ticketId) {
        return messageRepository.getMessageByTicket_Id(ticketId);
    }
    public Optional<Message> getMessageById(Long messageId) {
        return messageRepository.findById(messageId);
    }
}

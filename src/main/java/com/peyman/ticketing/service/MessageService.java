package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.MessageRequest;
import com.peyman.ticketing.dto.MessageResponse;
import com.peyman.ticketing.dto.mapper.MessageMapper;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.Message;
import com.peyman.ticketing.model.Ticket;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.repository.MessageRepository;
import com.peyman.ticketing.repository.TicketRepository;
import com.peyman.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final TicketRepository ticketRepository;
    private final UserService userService;
    public MessageService(MessageRepository messageRepository, TicketRepository ticketRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }
    public MessageResponse addMessage(Long ticketId, Long userId, MessageRequest request) {
        Message message = new Message();
        User user =userService.getEntityById(userId);
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException(("تیکت یافت نشد.")));
        message.setSentBy(user);
        message.setContent(request.getContent());
        message.setTicket(ticket);
        messageRepository.save(message);
        return (MessageMapper.mapMessageResponse(message));
    }
    public List<MessageResponse> getMessagesByTicketId(Long ticketId) {
        return messageRepository.getMessageByTicket_Id(ticketId).stream().map( MessageMapper::mapMessageResponse).collect(Collectors.toList());
    }
    public Message getEntityMessageById(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(()-> new ResourceNotFoundException("پیام یافت نشد"));
    }
    public MessageResponse getMessageById(Long messageId) {
        return messageRepository.findById(messageId).map(MessageMapper::mapMessageResponse).orElseThrow(()-> new ResourceNotFoundException("پیام یافت نشد"));
    }
}

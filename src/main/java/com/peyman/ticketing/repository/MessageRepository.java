package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.Message;
import com.peyman.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByTicket(Ticket ticket);
    List<Message> findByTicketOrderByCreatedAtAsc(Ticket ticket);

    List<Message> getMessageByTicket_Id(Long ticketId);
}

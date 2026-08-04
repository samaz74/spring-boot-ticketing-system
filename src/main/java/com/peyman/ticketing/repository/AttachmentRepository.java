package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.Attachment;
import com.peyman.ticketing.model.Message;
import com.peyman.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTicket(Ticket ticket);
    List<Attachment> findByMessage(Message message);

    List<Attachment> getAttachmentByTicket_Id(Long ticketId);

    List<Attachment> getAttachmentByMessage_Id(Long messageId);
}

package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.TicketRequest;
import com.peyman.ticketing.dto.TicketResponse;
import com.peyman.ticketing.dto.TicketingSystemResponse;
import com.peyman.ticketing.model.Ticket;

public class TicketMapper {
    public static Ticket toEntity(TicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setPriority(ticketRequest.getPriority());
        return ticket;
    }
    public static TicketResponse mapTicket(Ticket ticket){
        String assignedToName = ticket.getAssignedTo() != null
                ? ticket.getAssignedTo().getFirstName() + " " + ticket.getAssignedTo().getLastName()
                : null;
        return new TicketResponse(
                ticket.getId(),
                ticket.getTicketNumber(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getCreatedByUser().getFirstName() + " " + ticket.getCreatedByUser().getLastName(),
                assignedToName,
                ticket.getSubSystem().getName()
        );
    }
}

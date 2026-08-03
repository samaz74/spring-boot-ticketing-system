package com.peyman.ticketing.service;

import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.SupportAccess;
import com.peyman.ticketing.model.Ticket;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.model.enums.TicketStatus;
import com.peyman.ticketing.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final SubSystemService subSystemService;
    private final SupportAccessService supportAccessService;

    private TicketService(TicketRepository ticketRepository, UserService userService, SubSystemService subSystemService, SupportAccessService supportAccessService){
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.subSystemService = subSystemService;
        this.supportAccessService = supportAccessService;
    }
    public Ticket CreatTicket(Ticket ticket,Long userID, Long subSystemID){
        SubSystem subSystem = subSystemService.getById(subSystemID).get();
        User user = userService.getById(userID).get();
        String ticketNumber = subSystemService.generateTicketNumber(subSystemID);
        ticket.setCreatedByUser(user);
        ticket.setSubSystem(subSystem);
        ticket.setTicketNumber(ticketNumber);
        return ticketRepository.save(ticket);
    }
    public Optional<Ticket> getByID(Long id){
        return ticketRepository.findById(id);
    }
    public List<Ticket> getBySUbSystem(Long id){
        return ticketRepository.getTicketBySubSystem_Id(id);
    }
    public List<Ticket> getByAssignedTo(Long supporterID){
        return ticketRepository.getTicketByAssignedTo_Id(supporterID);
    }
    public void assignTicket (Long userId , Long ticketId){
        Ticket ticket = getByID(ticketId).get();
        ticket.setAssignedTo(userService.getById(userId).get());
    }
    public void changeStatus(Long ticketId, TicketStatus ticketStatus){
        Ticket ticket = getByID(ticketId).get();
        ticket.setStatus(ticketStatus);
        ticketRepository.save(ticket);
    }
    public List<Ticket> getVisibleTickets(Long userId) {
        List<SupportAccess> accessList = supportAccessService.getSupportAccessByUser_Id(userId);
        List<SubSystem> subSystems = accessList.stream()
                .map(SupportAccess::getSubSystem)
                .collect(Collectors.toList());
        User support = userService.getById(userId).get();

        List<Ticket> visibleTickets = new ArrayList<>();
        for (SubSystem subSystem : subSystems) {
            // تیکت‌های منتصب‌نشده
            visibleTickets.addAll(
                    ticketRepository.findBySubSystemAndAssignedToIsNull(subSystem,null)
            );
            // تیکت‌های منتصب به این پشتیبان
            visibleTickets.addAll(
                    ticketRepository.findBySubSystemAndAssignedTo(subSystem, support)
            );
        }
        return visibleTickets;

    }



}

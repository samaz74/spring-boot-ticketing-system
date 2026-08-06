package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.TicketRequest;
import com.peyman.ticketing.dto.TicketResponse;
import com.peyman.ticketing.dto.mapper.TicketMapper;
import com.peyman.ticketing.exeption.AccessDeniedException;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
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

    public TicketService(TicketRepository ticketRepository, UserService userService, SubSystemService subSystemService, SupportAccessService supportAccessService){
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.subSystemService = subSystemService;
        this.supportAccessService = supportAccessService;
    }
    public TicketResponse creatTicket(TicketRequest ticketRequest, Long userID, Long subSystemID){
        SubSystem subSystem = subSystemService.getEntityById(subSystemID);
        User user = userService.getEntityById(userID);
        String ticketNumber = subSystemService.generateTicketNumber(subSystemID);
        Ticket ticket = TicketMapper.toEntity(ticketRequest);
        ticket.setCreatedByUser(user);
        ticket.setSubSystem(subSystem);
        ticket.setTicketNumber(ticketNumber);
        ticketRepository.save(ticket);
        return TicketMapper.mapTicket(ticket);
    }
    public Ticket getEntityById(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("تیکت یافت نشد"));
    }
    public TicketResponse getById(Long id){
        return ticketRepository.findById(id).map(TicketMapper::mapTicket).orElseThrow(() -> new ResourceNotFoundException("تیکت یافت نشد"));
    }
    public List<TicketResponse> getBySUbSystem(Long id){
        return ticketRepository.getTicketBySubSystem_Id(id).stream().map(TicketMapper::mapTicket).collect(Collectors.toList());
    }
    public List<TicketResponse> getByUserId(Long id){
        return ticketRepository.getTicketByCreatedByUser_Id(id).stream().map(TicketMapper::mapTicket).collect(Collectors.toList());
    }
    public List<TicketResponse> getByAssignedTo(Long supporterID){
        return ticketRepository.getTicketByAssignedTo_Id(supporterID).stream().map(TicketMapper::mapTicket).collect(Collectors.toList());
    }
    public void assignTicket (Long userId , Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->new ResourceNotFoundException("تیکت یافت نشد."));
        if(supportAccessService.hasAccess(userId,ticket.getSubSystem().getId())){
            ticket.setAssignedTo(userService.getEntityById(userId));
            ticket.setStatus(TicketStatus.ASSIGNED);
            ticketRepository.save(ticket);
        }else throw new AccessDeniedException("کاربر به این سیستم دسترسی ندارد.");
    }
    public void changeStatus(Long ticketId, TicketStatus ticketStatus){
        Ticket ticket = getEntityById(ticketId);
        ticket.setStatus(ticketStatus);
        ticketRepository.save(ticket);
    }
    public List<TicketResponse> getVisibleTickets(Long userId) {
        List<SupportAccess> accessList = supportAccessService.getSupportAccessByUser_Id(userId);
        List<SubSystem> subSystems = accessList.stream()
                .map(SupportAccess::getSubSystem)
                .collect(Collectors.toList());
        User support = userService.getEntityById(userId);

        List<Ticket> visibleTickets = new ArrayList<>();
        for (SubSystem subSystem : subSystems) {
            // تیکت‌های منتصب‌نشده
            visibleTickets.addAll(
                    ticketRepository.findBySubSystemAndAssignedToIsNull(subSystem)
            );
            // تیکت‌های منتصب به این پشتیبان
            visibleTickets.addAll(
                    ticketRepository.findBySubSystemAndAssignedTo(subSystem, support)
            );
        }
        return visibleTickets.stream().map(TicketMapper::mapTicket).collect(Collectors.toList());

    }



}

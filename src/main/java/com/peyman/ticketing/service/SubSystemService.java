package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.SubSystemRequest;
import com.peyman.ticketing.dto.SubSystemResponse;
import com.peyman.ticketing.dto.TicketingSystemResponse;
import com.peyman.ticketing.dto.mapper.SubSystemMapper;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.TicketingSystem;
import com.peyman.ticketing.repository.SubSystemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubSystemService {
    private final SubSystemRepository subSystemRepository;
    private final TicketingSystemService ticketingSystemService;
    public SubSystemService(SubSystemRepository subSystemRepository, TicketingSystemService ticketingSystemService) {
        this.subSystemRepository = subSystemRepository;
        this.ticketingSystemService = ticketingSystemService;
    }
    public SubSystemResponse create(SubSystemRequest subSystemRequest , Long ticketingSystemID) {
        SubSystem subSystem = SubSystemMapper.toEntity(subSystemRequest);
        subSystem.setTicketingSystem(ticketingSystemService.getEntityById(ticketingSystemID));
        subSystemRepository.save(subSystem);
        return SubSystemMapper.toResponse(subSystem);
    }
    public SubSystem getEntityById(Long id){
        return subSystemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("زیرسیستم یافت نشد"));
    }
    public SubSystemResponse getById(Long id){
        return subSystemRepository.findById(id).map(SubSystemMapper::toResponse).orElseThrow(()->new ResourceNotFoundException("زیر سیستم یافت نشد."));
    }
    public List<SubSystemResponse> getBySystem(Long systemId){
        TicketingSystem ticketingSystem= ticketingSystemService.getEntityById(systemId);
        return subSystemRepository.findByTicketingSystem(ticketingSystem).stream().map(SubSystemMapper::toResponse).collect(Collectors.toList());
    }
    public void toggleActive(Long id){
        SubSystem subSystem= getEntityById(id);
        subSystem.setIsActive(!subSystem.getIsActive());
        subSystemRepository.save(subSystem);
    }
    @Transactional
    public String generateTicketNumber(Long id){
        SubSystem subSystem= getEntityById(id);
        int counter =subSystem.getTicketCounter();
        counter++;
        subSystem.setTicketCounter(counter);
        String prefix = subSystem.getPrefix();
        subSystemRepository.save(subSystem);
        return String.format("%s-%04d", prefix, counter);
    }
}

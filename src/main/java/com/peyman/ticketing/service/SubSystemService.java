package com.peyman.ticketing.service;

import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.TicketingSystem;
import com.peyman.ticketing.repository.SubSystemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubSystemService {
    private final SubSystemRepository subSystemRepository;
    private final TicketingSystemService ticketingSystemService;
    private SubSystemService(SubSystemRepository subSystemRepository, TicketingSystemService ticketingSystemService) {
        this.subSystemRepository = subSystemRepository;
        this.ticketingSystemService = ticketingSystemService;
    }
    public SubSystem create(SubSystem subSystem) {
        return subSystemRepository.save(subSystem);
    }
    public Optional<SubSystem> getById(Long id){
        return subSystemRepository.findById(id);
    }
    public List<SubSystem> getBySystem(Long systemId){
        TicketingSystem ticketingSystem= ticketingSystemService.getById(systemId).get();
        return subSystemRepository.findByTicketingSystem(ticketingSystem);
    }
    public void toggleActive(Long id){
        SubSystem subSystem= getById(id).get();
        subSystem.setIsActive(!subSystem.getIsActive());
        subSystemRepository.save(subSystem);
    }
    @Transactional
    public String generateTicketNumber(Long id){
        SubSystem subSystem= getById(id).get();
        int counter =subSystem.getTicketCounter();
        counter++;
        subSystem.setTicketCounter(counter);
        String prefix = subSystem.getPrefix();
        subSystemRepository.save(subSystem);
        return String.format("%s-%04d", prefix, counter);
    }
}

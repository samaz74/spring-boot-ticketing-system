package com.peyman.ticketing.service;

import com.peyman.ticketing.model.TicketingSystem;
import com.peyman.ticketing.repository.TicketingSystemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketingSystemService {
    private final TicketingSystemRepository ticketingSystemRepository;
    private TicketingSystemService(TicketingSystemRepository ticketingSystemRepository) {
        this.ticketingSystemRepository = ticketingSystemRepository;
    }
    public TicketingSystem create(TicketingSystem ticketingSystem) {
        String apiKey = UUID.randomUUID().toString();
        ticketingSystem.setApiKey(apiKey);
        return ticketingSystemRepository.save(ticketingSystem);
    }
    public List<TicketingSystem> getAll() {
        return ticketingSystemRepository.findAll();
    }
    public Optional<TicketingSystem> getById(Long id) {
        return ticketingSystemRepository.findById(id);
    }
    public void toggleActive(long id ) {
        TicketingSystem ticketingSystem =  getById(id).get();
        ticketingSystem.setActive(!ticketingSystem.getActive());
        ticketingSystemRepository.save(ticketingSystem);
    }
    public Optional<TicketingSystem> getByApiKey(String apiKey) {
        return ticketingSystemRepository.findByApiKey(apiKey);
    }
}

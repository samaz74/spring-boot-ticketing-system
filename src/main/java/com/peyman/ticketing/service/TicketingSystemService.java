package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.TicketingSystemRequest;
import com.peyman.ticketing.dto.TicketingSystemResponse;
import com.peyman.ticketing.dto.mapper.TicketingSystemMapper;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
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
    public TicketingSystemService(TicketingSystemRepository ticketingSystemRepository) {
        this.ticketingSystemRepository = ticketingSystemRepository;
    }
    public TicketingSystemResponse create(TicketingSystemRequest request) {
        String apiKey = UUID.randomUUID().toString();
        TicketingSystem ticketingSystem = TicketingSystemMapper.toEntity(request);
        ticketingSystem.setApiKey(apiKey);
        ticketingSystemRepository.save(ticketingSystem);
        return TicketingSystemMapper.mapperResponse(ticketingSystem);
    }
    public List<TicketingSystemResponse> getAll() {
        return ticketingSystemRepository.findAll().stream().map(TicketingSystemMapper::mapperResponse).collect(Collectors.toList());
    }
    public TicketingSystem getEntityById(Long id) {
        return ticketingSystemRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("شخصی با این شناسه یافت نشد"));
    }
    public TicketingSystemResponse getById(Long id) {
        return ticketingSystemRepository.findById(id).map(TicketingSystemMapper::mapperResponse).orElseThrow(() ->new ResourceNotFoundException("شخصی با این شناسه یافت نشد"));
    }
    public void toggleActive(long id ) {
        TicketingSystem ticketingSystem =  getEntityById(id);
        ticketingSystem.setActive(!ticketingSystem.getActive());
        ticketingSystemRepository.save(ticketingSystem);
    }
    public TicketingSystemResponse getByApiKey(String apiKey) {
        return ticketingSystemRepository.findByApiKey(apiKey).map(TicketingSystemMapper::mapperResponse).orElseThrow(() -> new ResourceNotFoundException("سیستم تیکتینگ یافت نشد."));
    }
}

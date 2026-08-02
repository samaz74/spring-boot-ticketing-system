package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.TicketingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketingSystemRepository extends JpaRepository<TicketingSystem,Long> {
    Optional<TicketingSystem> findByApiKey(String apiKey);
    Optional<TicketingSystem> findByName(String name);
}

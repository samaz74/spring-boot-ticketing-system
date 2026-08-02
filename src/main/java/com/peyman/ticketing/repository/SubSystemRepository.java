package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.TicketingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubSystemRepository extends JpaRepository<SubSystem,Long> {
    List<SubSystem> findByTicketingSystem(TicketingSystem system);
    Optional<SubSystem> findByPrefix(String prefix);
}

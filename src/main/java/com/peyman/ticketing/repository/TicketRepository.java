package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.Ticket;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarFile;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySubSystem(SubSystem subSystem);
    List<Ticket> findByAssignedTo(User assignedTo);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findBySubSystemAndStatus(SubSystem subSystem, TicketStatus status);
    Optional<Ticket> findByTicketNumber(String ticketNumber);

    List<Ticket> getTicketById(Long id);

    List<Ticket> getTicketBySubSystem(SubSystem subSystem);

    List<Ticket> getTicketBySubSystem_Id(Long subSystemId);

    List<Ticket> getTicketByAssignedTo_Id(Long assignedToId);

    List<Ticket> getTicketByAssignedTo_IdOrAssignedTo(Long assignedToId, User assignedTo);

    List<Ticket> getTicketByAssignedTo_IdOrAssignedToNull(Long assignedToId, User assignedTo);

    List<Ticket> getTicketByAssignedTo_IdOrAssignedToNullAndSubSystem(Long assignedToId, User assignedTo, SubSystem subSystem);

    Ticket[] getTicketByAssignedTo(User assignedTo);

    Collection<? extends Ticket> findBySubSystemAndAssignedToIsNull(SubSystem subSystem);

    Collection<? extends Ticket> findBySubSystemAndAssignedTo(SubSystem subSystem, User assignedTo);

    List<Ticket> getTicketByCreatedByUser_Id(Long createdByUserId);
}

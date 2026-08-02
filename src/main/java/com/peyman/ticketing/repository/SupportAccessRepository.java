package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.SupportAccess;
import com.peyman.ticketing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportAccessRepository extends JpaRepository<SupportAccess,Long> {
    List<SupportAccess> findByUser(User user);
    List<SupportAccess> findBySubSystem(SubSystem subSystem);
    boolean existsByUserAndSubSystem(User user, SubSystem subSystem);
}

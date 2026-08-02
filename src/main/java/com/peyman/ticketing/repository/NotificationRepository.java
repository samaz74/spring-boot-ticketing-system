package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.Notification;
import com.peyman.ticketing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsReadFalse(User user);
    List<Notification> findByUser(User user);
    long countByUserAndIsReadFalse(User user);
}

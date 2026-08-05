package com.peyman.ticketing.repository;

import com.peyman.ticketing.model.Notification;
import com.peyman.ticketing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsReadFalse(User user);
    List<Notification> findByUser(User user);
    long countByUserAndIsReadFalse(User user);

    List<Notification> getNotificationByIsRead(Boolean isRead);

    Optional <Notification> findNotificationById(Long id);

    int countNotificationByUser_Id(Long userId);

    List<Notification> getNotificationByIsReadAndUser_Id(Boolean isRead, Long userId);

    int countNotificationByUser_IdAndIsRead(Long userId, Boolean isRead);
}

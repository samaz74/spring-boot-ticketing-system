package com.peyman.ticketing.service;

import com.peyman.ticketing.model.Notification;
import com.peyman.ticketing.model.enums.NotificationType;
import com.peyman.ticketing.repository.NotificationRepository;
import com.peyman.ticketing.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final TicketService ticketService;
    private final UserService userService;
    private NotificationService(NotificationRepository notificationRepository, TicketService ticketService, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.ticketService = ticketService;
        this.userService = userService;
    }
    public Notification createNotification(long userId , Long ticketId,String content, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setType(notificationType);
        notification.setTicket(ticketService.getById(ticketId).get());
        notification.setUser(userService.getById(userId).get());
        return notificationRepository.save(notification);
    }
    public List<Notification> getUnread(Long userId) {
        return notificationRepository.getNotificationByIsReadAndUser_Id(false,userId);
    }
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findNotificationById(notificationId);
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
    public int countUnread(Long userId) {
        return notificationRepository.countNotificationByUser_IdAndIsRead(userId,false);
    }
}

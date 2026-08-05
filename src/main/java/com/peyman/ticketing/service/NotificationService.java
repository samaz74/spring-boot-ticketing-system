package com.peyman.ticketing.service;

import com.peyman.ticketing.dto.NotificationResponse;
import com.peyman.ticketing.dto.mapper.NotificationMapper;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.Notification;
import com.peyman.ticketing.model.enums.NotificationType;
import com.peyman.ticketing.repository.NotificationRepository;
import com.peyman.ticketing.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public NotificationResponse createNotification(long userId , Long ticketId, String content, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setType(notificationType);
        notification.setTicket(ticketService.getEntityById(ticketId));
        notification.setUser(userService.getEntityById(userId));
        notificationRepository.save(notification);
        return NotificationMapper.mapNotification(notification);
    }
    public List<NotificationResponse> getUnread(Long userId) {
        return notificationRepository.getNotificationByIsReadAndUser_Id(false,userId).stream().map(NotificationMapper::mapNotification).collect(Collectors.toList());
    }
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findNotificationById(notificationId).orElseThrow(()->new ResourceNotFoundException("یافت نشد."));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
    public int countUnread(Long userId) {
        return notificationRepository.countNotificationByUser_IdAndIsRead(userId,false);
    }
}

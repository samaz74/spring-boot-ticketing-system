package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.NotificationResponse;
import com.peyman.ticketing.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping("/user/{userId}")
    public List<NotificationResponse> getNotifications(@PathVariable Long userId) {
        return notificationService.getUnread(userId);
    }
    @PatchMapping("/{id}/read")
    public void markRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }
    @PatchMapping("/readAll/{userId}")
    public void markReadAll(@PathVariable Long userId) {
        notificationService.markAsReadAll(userId);
    }
}

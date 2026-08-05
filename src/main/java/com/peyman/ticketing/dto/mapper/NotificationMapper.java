package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.NotificationResponse;
import com.peyman.ticketing.model.Notification;

public class NotificationMapper {
    public static NotificationResponse mapNotification(Notification notification){
        return new NotificationResponse(
                notification.getId(),
                notification.getContent(),
                notification.getType(),
                notification.getIsRead(),
                notification.getCreatedAt()
        );
    }
}

package com.team0301.NotificationService.service;

import com.team0301.NotificationService.dto.NotificationRequest;
import com.team0301.NotificationService.dto.NotificationResponse;
import com.team0301.NotificationService.model.Notification;
import com.team0301.NotificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void createNotification(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .userId(notificationRequest.getUserId())
                .eventId(notificationRequest.getEventId())
                .updatedInfo(notificationRequest.getUpdatedInfo())
                .userEmail(notificationRequest.getUserEmail())
                .build();

        notificationRepository.save(notification);
        log.info("Notification {} is saved", notification.getId());
    }

    public List<NotificationResponse> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();

        return notifications.stream().map(this:: mapToNotificationResponse).toList();
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .eventId(notification.getEventId())
                .userEmail(notification.getUserEmail())
                .updatedInfo(notification.getUpdatedInfo())
                .build();
    }
}

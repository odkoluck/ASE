package com.team0301.NotificationService.repository;

import com.team0301.NotificationService.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

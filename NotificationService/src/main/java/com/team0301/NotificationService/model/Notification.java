package com.team0301.NotificationService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Notification {
    @Id
    private String id;
    private String userId;
    private String eventId;
    private String userEmail;
    private String updatedInfo;
}

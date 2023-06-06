package com.team0301.NotificationService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String userId;
    private String eventId;
    private String userEmail;
    private String updatedInfo;
}

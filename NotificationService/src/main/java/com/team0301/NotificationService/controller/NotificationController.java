package com.team0301.NotificationService.controller;

import com.team0301.NotificationService.dto.NotificationRequest;
import com.team0301.NotificationService.dto.NotificationResponse;
import com.team0301.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNotification(@RequestBody NotificationRequest notificationRequest){
        notificationService.createNotification(notificationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponse> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

}

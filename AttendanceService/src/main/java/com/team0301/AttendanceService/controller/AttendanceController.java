package com.team0301.AttendanceService.controller;

import com.team0301.AttendanceService.scheme.ResponseScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.team0301.AttendanceService.model.Attendance;
import com.team0301.AttendanceService.repository.AttendanceRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceRepository registerRepo;
    @PostMapping("/attendance/eventRegistration")
    public ResponseScheme eventRegister(@RequestParam String eventId, String attendeeId) {
        try {
            Attendance attendance = new Attendance();
            attendance.setEventId(eventId);
            attendance.setAttendeeId(attendeeId);
            registerRepo.insert(attendance);
            return ResponseScheme.getInstance(true);
        } catch (Exception e) {
            return ResponseScheme.getInstance(false, e.getMessage());
        }
    }



}

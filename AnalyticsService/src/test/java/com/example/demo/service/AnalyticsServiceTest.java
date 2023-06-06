package com.example.demo.service;

import com.example.demo.controller.AnalyticsMainController;
import com.example.demo.model.Event;
import com.example.demo.model.Feedback;
import com.example.demo.model.RegistredAttendee;
import com.example.demo.model.ReportEvent;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.RegisteredAttendeeRepository;
import com.example.demo.repository.ReportEventRepository;
import com.example.demo.responseScheme.FeedbackResponseScheme;
import com.example.demo.responseScheme.ReportResponseScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AnalyticsServiceTest {

    @InjectMocks
    private AnalyticsService analyticsService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private FeedbackRepository feedbackRepository;


    @Test
    public void hello() {
        AnalyticsService analyticsService = new AnalyticsService();

        String response = analyticsService.hello("World");
        assertEquals("Hello World!", response);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void AnalyticsService_ShouldReturnAllEvents() {

        List<Event> events = new ArrayList<>(Arrays.asList(
                new Event("1", "java", "2023.01.01", "2023.01.02", "uni", "kkkk"),
                new Event("2", "nodeJS", "2023.05.01", "2023.05.02", "uni", "jjj")
        ));

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> myResponse = analyticsService.getAllEvents();

        assertEquals(events, myResponse);
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    public void AnalyticsService_ShouldReturnAllFeedbacks() {

        List<FeedbackResponseScheme> feedbackResponseList = new ArrayList<>();

        List<Event> events = new ArrayList<>(Arrays.asList(
                new Event("1", "java", "2022.01.01", "2022.01.02", "uni", "kkkk"),
                new Event("2", "nodeJS", "2022.05.01", "2022.05.02", "uni", "jjj")
        ));

        when(eventRepository.findAll()).thenReturn(events);

        List<Feedback> feedbacks = new ArrayList<>(Arrays.asList(
                new Feedback("111", "1", "1", 5, 5, 4, 4, "good"),
                new Feedback("112", "1", "2", 4, 3, 2, 3, "okok"),
                new Feedback("113", "1", "3", 5, 3, 1, 4, "yes"),
                new Feedback("114", "2", "3", 5, 3, 1, 4, "yes"),
                new Feedback("114", "2", "3", 5, 3, 1, 4, "yes")
        ));

        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        when(eventRepository.findById("1")).thenReturn(Optional.ofNullable(events.get(0)));
        when(eventRepository.findById("2")).thenReturn(Optional.ofNullable(events.get(1)));

        feedbackResponseList = analyticsService.getAllFeedbacks();

        List<FeedbackResponseScheme> expected = new ArrayList<>(Arrays.asList(
                new FeedbackResponseScheme("java", 14.0 / 3.0, 11.0 / 3.0, 7.0 / 3.0, 11.0 / 3.0),
                new FeedbackResponseScheme("nodeJS", 10.0 / 2.0, 6.0 / 2.0, 2.0 / 2.0, 8.0 / 2.0)
        ));

        assertEquals(expected.size(), feedbackResponseList.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).hashCode(), feedbackResponseList.get(i).hashCode());
        }
    }

    @Test
    public void AnalyticsService_ShouldReturnAnEvent_ByID() {
        Event event = new Event("1", "java", "2022.01.01", "2022.01.02", "uni", "kkkk");

        when(eventRepository.findById("1")).thenReturn(Optional.of(event));

        List<Feedback> feedbacks = new ArrayList<>(Arrays.asList(
                new Feedback("111", "1", "1", 5, 5, 4, 4, "good"),
                new Feedback("112", "1", "2", 4, 3, 2, 3, "okok"),
                new Feedback("113", "1", "3", 5, 3, 1, 4, "yes")
        ));

        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        FeedbackResponseScheme response = analyticsService.getFeedbackByEventId("1");

        FeedbackResponseScheme expected = new FeedbackResponseScheme("java", 14.0 / 3.0, 11.0 / 3.0, 7.0 / 3.0, 11.0 / 3.0);

        assertEquals(expected.hashCode(), response.hashCode());
    }

    @Mock
    private RegisteredAttendeeRepository registeredAttendeeRepository;

    @Mock
    ReportEventRepository reportEventRepository;

    @Test
    public void AnalyticsService_ShouldReturnReports_ForAllEvents() {
        List<RegistredAttendee> registeredAttendee = new ArrayList<>(Arrays.asList(
                new RegistredAttendee(1, 1),
                new RegistredAttendee(2, 1),
                new RegistredAttendee(3, 1),
                new RegistredAttendee(4, 1),
                new RegistredAttendee(5, 2),
                new RegistredAttendee(6, 2)
        ));

        when(registeredAttendeeRepository.findAll()).thenReturn(registeredAttendee);

        List<Event> events = new ArrayList<>(Arrays.asList(
                new Event("1", "java", "2022.01.01", "2040.01.02", "uni", "kkkk"),
                new Event("2", "nodeJS", "2022.05.01", "2040.05.02", "uni", "jjj")
        ));

        when(eventRepository.findAll()).thenReturn(events);

        when(reportEventRepository.existsById(any())).thenReturn(false);

        List<ReportResponseScheme> response = analyticsService.generateReportForAllEvents();

        List<ReportResponseScheme> expected = new ArrayList<>(Arrays.asList(
                new ReportResponseScheme("java", LocalDate.now(), 4, 3),
                new ReportResponseScheme("nodeJS", LocalDate.now(), 2, 3)
        ));

        assertEquals(expected.size(), response.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).hashCode(), response.get(i).hashCode());
        }
    }

    @Test
    public void AnalyticsService_ShouldReturnAReport_ByEventID() {
        ReportEvent report = new ReportEvent("1", "nodeJS", 5, 3, LocalDate.now());

        when(reportEventRepository.findById("1")).thenReturn(Optional.of(report));

        ReportResponseScheme response = analyticsService.getReportByEventId("1");

        ReportResponseScheme expected = new ReportResponseScheme(report.getEventName(), report.getReportGeneratedDate(), report.getCountRegistered(), report.getCountAttended());

        assertEquals(expected.hashCode(), response.hashCode());
    }
}
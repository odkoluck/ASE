package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.responseScheme.ReportResponseScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.responseScheme.FeedbackResponseScheme;

@Service
public class AnalyticsService {

    @Autowired
    AnalyticsFeedbackRepository analyticsFeedbackRepository;

    @Autowired
    ReportEventRepository reportEventRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    RegisteredAttendeeRepository registeredAttendeeRepository;

    @Autowired
    EventRepository eventRepository;


    public String hello(String name) {
        return String.format("Hello %s!", name);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get All Events Feedback
    public List<FeedbackResponseScheme> getAllFeedbacks() {

        List<Feedback> feedbackList = feedbackRepository.findAll();
        //List<AnalyticsFeedback> analyticsFeedbackList = analyticsFeedbackRepository.findAll();

        //List<Event> eventList = new ArrayList<>();
        List<FeedbackResponseScheme> feedbackResponseList = new ArrayList<>();

        // prepare set for eventIdset
        Set<String> eventIdSet = new HashSet<>();

        // collect eventIdSet
        for (Feedback feed : feedbackList) {
            eventIdSet.add(feed.getEventId());
        }

        System.out.println(eventIdSet);


        try {
            for (String eventId : eventIdSet) {

                double location = 0;
                double accuracy = 0;
                double performance = 0;
                double rating = 0;
                double sizeOfFeedback = 0;

                for (Feedback feedback : feedbackList) {
                    if (feedback.getEventId().equals(eventId)) {
                        sizeOfFeedback++;
                        location = location + feedback.getLocation();
                        accuracy = accuracy + feedback.getAccuracy();
                        performance = performance + feedback.getPerformance();
                        rating = rating + feedback.getRating();
                    }
                }

                System.out.println("location: " + location);
                System.out.println("accuracy: " + accuracy);
                System.out.println("performance: " + performance);
                System.out.println("rating: " + rating);
                System.out.println("sizeOfFeedback: " + sizeOfFeedback);


					/*if(analyticsFeedbackRepository.existsById(eventId)){
						analyticsFeedbackRepository.deleteById(eventId);
						AnalyticsFeedback analyticsFeedback = new AnalyticsFeedback( eventId, eventRepository.findById(eventId).get().getEventName(), location/sizeOfFeedback, accuracy/sizeOfFeedback, performance/sizeOfFeedback, rating/sizeOfFeedback, LocalDate.now());
						analyticsFeedbackRepository.insert(analyticsFeedback);
					}
					else {
						AnalyticsFeedback analyticsFeedback = new AnalyticsFeedback( eventId, eventRepository.findById(eventId).get().getEventName(), location/sizeOfFeedback, accuracy/sizeOfFeedback, performance/sizeOfFeedback, rating/sizeOfFeedback, LocalDate.now());
						analyticsFeedbackRepository.insert(analyticsFeedback);
					}*/

                FeedbackResponseScheme feedbackResponseScheme = new FeedbackResponseScheme(eventRepository.findById(eventId).get().getEventName(), location / sizeOfFeedback, accuracy / sizeOfFeedback, performance / sizeOfFeedback, rating / sizeOfFeedback);
                feedbackResponseList.add(feedbackResponseScheme);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return feedbackResponseList;

    }

    // get Feedback by EventId
    public FeedbackResponseScheme getFeedbackByEventId(String eventId) {

        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList = feedbackRepository.findAll();

        System.out.println(eventRepository.findById(eventId).get().getEventName());

        double location = 0;
        double accuracy = 0;
        double performance = 0;
        double rating = 0;
        double sizeOfFeedback = 0;
        try {

            for (Feedback feedback : feedbackList) {
                if (feedback.getEventId().equals(eventId)) {
                    sizeOfFeedback++;
                    location = location + feedback.getLocation();
                    accuracy = accuracy + feedback.getAccuracy();
                    performance = performance + feedback.getPerformance();
                    rating = rating + feedback.getRating();
                }
            }

            System.out.println("location: " + location);
            System.out.println("accuracy: " + accuracy);
            System.out.println("performance: " + performance);
            System.out.println("rating: " + rating);
            System.out.println("sizeOfFeedback: " + sizeOfFeedback);


            FeedbackResponseScheme feedbackResponseScheme = new FeedbackResponseScheme(eventRepository.findById(eventId).get().getEventName(), location / sizeOfFeedback, accuracy / sizeOfFeedback, performance / sizeOfFeedback, rating / sizeOfFeedback);


            return feedbackResponseScheme;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    // Report

    public List<ReportResponseScheme> generateReportForAllEvents() {
        List<RegistredAttendee> registerList = registeredAttendeeRepository.findAll();
        List<Event> allEventList = eventRepository.findAll();
        List<Event> activeEventList = new ArrayList<>();

        List<ReportResponseScheme> responseSchemes = new ArrayList<>();

        try {
            for (Event event : allEventList) {
                LocalDate today = java.time.LocalDate.now();
                System.out.println(today);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate endDate = LocalDate.parse(event.getEndDate(), formatter);

                if (endDate.isAfter(today)) {
                    activeEventList.add(event);
                    System.out.println(event.getEventName());
                    System.out.println(event.getEventId());
                    Integer registeredPeople = 0;

                    for (RegistredAttendee registredAttendee : registerList) {
                        System.out.println("okok");
                        System.out.println(registredAttendee.getRegistredEventId());
                        if (event.getEventId().toString().equals(registredAttendee.getRegistredEventId().toString())) {
                            System.out.println(registredAttendee.getRegistredEventId());
                            System.out.println(event.getEventId());

                            registeredPeople++;
                        }
                    }

                    // delete if already created this event report, the new  report to create.
                    if (reportEventRepository.existsById(event.getEventId())) {
                        reportEventRepository.deleteById(event.getEventId().toString());
                    }

                    // create new reportEvent
                    ReportEvent reportEvent = new ReportEvent(event.getEventId(), event.getEventName(), registeredPeople, 10, LocalDate.now());
                    reportEventRepository.insert(reportEvent);

                    ReportResponseScheme responseReport = new ReportResponseScheme(event.getEventName(), LocalDate.now(), registeredPeople, 3);
                    responseSchemes.add(responseReport);


                }

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return responseSchemes;

    }

    public ReportResponseScheme getReportByEventId(String eventId) {

        ReportEvent reportEvent = reportEventRepository.findById(eventId).get();
        return new ReportResponseScheme(reportEvent.getEventName(), reportEvent.getReportGeneratedDate(), reportEvent.getCountRegistered(), reportEvent.getCountAttended());
    }


}

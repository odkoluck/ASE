package com.example.demo.controller;

import java.util.*;

import com.example.demo.responseScheme.ReportResponseScheme;
import com.example.demo.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AnalyticsFeedbackRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.responseScheme.FeedbackResponseScheme;
import com.example.demo.model.Event;


@RestController
public class AnalyticsMainController {
	
	@Autowired
	AnalyticsFeedbackRepository analyticsFeedbackRepository;
	

	@Autowired
	private AnalyticsService analyticsService;

	@Autowired
	EventRepository eventRepository;
	
	@GetMapping("/mytest")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return analyticsService.hello(name) ;
    }

	@GetMapping("/kkk")
	public List<Event> getAllEvent(){
		return analyticsService.getAllEvents();
	}


	@GetMapping("/feedbacks")
	public List<FeedbackResponseScheme> getAllFeedbacks() {
		return analyticsService.getAllFeedbacks();
	}
	
	@GetMapping("/feedback/{id}")
	public FeedbackResponseScheme getFeedbackByEventId(@PathVariable("id") String eventId) {
		return analyticsService.getFeedbackByEventId(eventId);
	}

	@GetMapping("/reports")
	public List<ReportResponseScheme> getAllReports() {
		return analyticsService.generateReportForAllEvents();
	}

	@GetMapping("/report/{eventId}")
	public ReportResponseScheme getReportByEventId(@PathVariable("eventId") String eventId) {

		return analyticsService.getReportByEventId(eventId);
	}


}

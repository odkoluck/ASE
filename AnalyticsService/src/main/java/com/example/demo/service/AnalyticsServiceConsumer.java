package com.example.demo.service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Event;
import com.example.demo.model.RegistredAttendee;
import com.example.demo.repository.AnalyticsFeedbackRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.RegisteredAttendeeRepository;



@Component
public class AnalyticsServiceConsumer {
	
	
	@Autowired
	private RegisteredAttendeeRepository registeredAttendeeRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;

	
	@RabbitListener(queues ="eventQueue" )
	public void consumerExportServiceQueue(Event event) {
		
		System.out.println(event.getEventId());
		System.out.println(event.getEventName());
		System.out.println(event.getStartDate());
		System.out.println(event.getDescription());
		System.out.println(event.getLocation());
		
		
		try {
			Event newEvent = new Event();
			newEvent.setEventName(event.getEventName());
			newEvent.setEventId(event.getEventId());
			newEvent.setStartDate(event.getStartDate());
			newEvent.setEndDate(event.getEndDate());
			newEvent.setDescription(event.getDescription());
			newEvent.setLocation(event.getLocation());
			eventRepository.insert(newEvent);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	
	@RabbitListener(queues ="test_Register_Queue" )
	public void registredAttendeeQueue(RegistredAttendee registredAttendee) {
		
		//System.out.println(registredAttendee.getId());
		System.out.println(registredAttendee.getUserId());
		System.out.println(registredAttendee.getRegistredEventId());
		
		
		try {
			RegistredAttendee registeredEvent = new RegistredAttendee();
			registeredEvent.setUserId(registredAttendee.getUserId());;
			registeredEvent.setRegistredEventId(registredAttendee.getRegistredEventId());
			registeredAttendeeRepository.insert(registeredEvent);
			
		}catch(Exception e) {
			System.out.println("nono");
		}	
	}
	

	@RabbitListener(queues ="FB_Queue" )
	public void feedbackQueue(Feedback feedback) {
		
		//System.out.println(feedback.getId());
		System.out.println(feedback.getUserId());
		System.out.println(feedback.getEventId());
		System.out.println(feedback.getFeedbackId());
		System.out.println(feedback.getDescription());
		
		
		try {
			Feedback feedbackEvent = new Feedback();
			feedbackEvent.setFeedbackId(feedback.getFeedbackId());
			feedbackEvent.setUserId(feedback.getUserId());;
			feedbackEvent.setEventId(feedback.getEventId());
			feedbackEvent.setAccuracy(feedback.getAccuracy());
			feedbackEvent.setDescription(feedback.getDescription());
			feedbackEvent.setLocation(feedback.getLocation());
			feedbackEvent.setPerformance(feedback.getPerformance());
			feedbackEvent.setRating(feedback.getRating());
			feedbackRepository.insert(feedbackEvent);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}

}

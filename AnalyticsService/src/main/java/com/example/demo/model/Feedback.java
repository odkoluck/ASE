package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="feedback")
public class Feedback {
	
	@Id
	private String feedbackId;
	
    private String eventId;
    private String userId;
    private Integer location;
    private Integer accuracy;
    private Integer performance;
    private double rating;
    private String description;
    
    public Feedback() {
		this("", "", "", 0, 0, 0, 0, "");
	}
    
	public Feedback(String feedbackId, String eventId, String userId, Integer location,
					Integer accuracy, Integer performance, double rating, String description) {
		
		this.feedbackId = feedbackId;
		this.eventId = eventId;
		this.userId = userId;
		this.location = location;
		this.accuracy = accuracy;
		this.performance = performance;
		this.rating = rating;
		this.description = description;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public Integer getPerformance() {
		return performance;
	}

	public void setPerformance(Integer performance) {
		this.performance = performance;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	


}

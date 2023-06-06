package com.example.demo.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Feedback {

    @JsonProperty("feedbackId")
    @Id
    private String feedbackId;
    private String eventId;
    private String userId;
    private int location;
    private int accuracy;
    private int performance;
    private double rating;
    private String description;

    public Feedback() {}

    public Feedback(String eventId, String userId, int location, int accuracy, int performance, String description) {
        this.eventId = eventId;
        this.userId = userId;
        this.location = location;
        this.accuracy = accuracy;
        this.performance = performance;
        this.description = description;
        this.rating = calculateRating();
    }



    public String getEventId() {
        return eventId;
    }

    public String getFeedbackId() {
        return feedbackId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
        this.rating = calculateRating();
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
        this.rating = calculateRating();
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
        this.rating = calculateRating();
    }

    public double getRating() {
        return rating;
    }

    //Calculate the overall rating by the mean of location, accuracy and performance.
    public double calculateRating() {
        float result = (location + accuracy + performance)/3.0f;
        return Math.round(result * 100.0) / 100.0;
    }
}


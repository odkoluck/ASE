package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection="analyticsFeedback")
public class AnalyticsFeedback {
    @Id
    private String eventId;
    private String eventName;
    private double  location;
    private double accuracy;
    private double performance;
    private double rating;
    private LocalDate analyticsFeedbackDate;



    public AnalyticsFeedback(){
        this("","", 0L, 0L, 0L, 0L, null );
    }

    public AnalyticsFeedback(String eventId, String eventName, double location, double accuracy, double performance, double rating, LocalDate analyticsFeedbackDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.accuracy = accuracy;
        this.performance = performance;
        this.rating = rating;
        this.analyticsFeedbackDate = analyticsFeedbackDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDate getAnalyticsFeedbackDate() {
        return analyticsFeedbackDate;
    }

    public void setAnalyticsFeedbackDate(LocalDate analyticsFeedbackDate) {
        this.analyticsFeedbackDate = analyticsFeedbackDate;
    }
}

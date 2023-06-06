package com.example.demo.responseScheme;

import java.util.List;
import java.util.Objects;

public class FeedbackResponseScheme {

    private String eventName;
    private double location;
    private double accuracy;
    private double performance;
    private double rating;
    private List<String> description;

    public FeedbackResponseScheme() {
        this("", 0, 0, 0, 0);
    }

    public FeedbackResponseScheme(String eventName, double location, double accuracy, double performance,
                                  double rating) {
        this.eventName = eventName;
        this.location = location;
        this.accuracy = accuracy;
        this.performance = performance;
        this.rating = rating;
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

    @Override
    public int hashCode() {
        return Objects.hash(eventName, location, accuracy, performance, rating);
    }

    public static FeedbackResponseScheme getInstance(String eventName, double location, double accuracy, double performance,
                                                     double rating) {

        return new FeedbackResponseScheme(eventName, location, accuracy, performance, rating);

    }

}

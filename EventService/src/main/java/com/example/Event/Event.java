package com.example.Event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document
public class Event {
    @Id
    private String id;
    private String startDate;
    private String endDate;
    private String eventName;
    private String location;
    private String startTime;
    private Integer capacity;
    private String type;
    private String description;
    private String emailAddress;


    public Event(String id, String startDate, String endDate, String eventName, String location, String startTime, Integer capacity, String type, String description, String emailAddress) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventName = eventName;
        this.location = location;
        this.startTime = startTime;
        this.capacity = capacity;
        this.type = type;
        this.description = description;
        this.emailAddress = emailAddress;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEndDate(){return endDate;}

    public void setEndDate(String endDate){this.endDate = endDate;}

}

package com.example.demo.responseScheme;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.time.LocalDate;
import java.util.Objects;

public class ReportResponseScheme {

    private String eventName;
    private LocalDate reportGeneratedDate;
    private Integer registeredPeople;
    private Integer bookmarksPeople;

    public ReportResponseScheme(String eventName, LocalDate reportGeneratedDate, Integer registeredPeople, Integer bookmarksPeople) {
        this.eventName = eventName;
        this.reportGeneratedDate = reportGeneratedDate;
        this.registeredPeople = registeredPeople;
        this.bookmarksPeople = bookmarksPeople;
    }

    public String getEventName() {
        return eventName;
    }

    public Integer getRegisteredPeople() {
        return registeredPeople;
    }

    public Integer getBookmarksPeople() {
        return bookmarksPeople;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setRegisteredPeople(Integer registeredPeople) {
        this.registeredPeople = registeredPeople;
    }

    public void setBookmarksPeople(Integer bookmarksPeople) {
        this.bookmarksPeople = bookmarksPeople;
    }

    public LocalDate getReportGeneratedDate() {
        return reportGeneratedDate;
    }

    public void setReportGeneratedDate(LocalDate reportGeneratedDate) {
        this.reportGeneratedDate = reportGeneratedDate;
    }

    public static ReportResponseScheme getInstance(String eventName, LocalDate reportGeneratedDate, Integer registeredPeople, Integer bookmarksPeople){
        return  new ReportResponseScheme(eventName, reportGeneratedDate, registeredPeople, bookmarksPeople);
    }

    /**
     * if we want to compare self defined objects, we should modifie/override the standard hashCode method
     * since Java will compare class Object with Objects.equals(`other object`)
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventName, reportGeneratedDate, registeredPeople, bookmarksPeople);
    }
}

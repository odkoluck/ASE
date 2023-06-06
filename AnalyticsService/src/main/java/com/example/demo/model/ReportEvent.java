package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection="ReportEvent")
public class ReportEvent {

	@Id
	private String eventId;
	private String eventName;
	private Integer countRegistered;
	private Integer countAttended;
	private Integer countBookmarked;
	private LocalDate reportGeneratedDate;

	public ReportEvent(String eventId, String eventName, Integer countRegistered, Integer countBookmarked, LocalDate reportGeneratedDate) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.countRegistered = countRegistered;
		// this.countAttended = countAttended;
		this.countBookmarked = countBookmarked;
		this.reportGeneratedDate = reportGeneratedDate;
	}

	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Integer getCountRegistered() {
		return countRegistered;
	}
	public void setCountRegistered(Integer countRegistered) {
		this.countRegistered = countRegistered;
	}
	public Integer getCountAttended() {
		return countAttended;
	}
	public void setCountAttended(Integer countAttended) {
		this.countAttended = countAttended;
	}
	public Integer getCountBookmarked() {
		return countBookmarked;
	}
	public void setCountBookmarked(Integer countBookmarked) {
		this.countBookmarked = countBookmarked;
	}

	public LocalDate getReportGeneratedDate() {
		return reportGeneratedDate;
	}

	public void setReportGeneratedDate(LocalDate reportGeneratedDate) {
		this.reportGeneratedDate = reportGeneratedDate;
	}
}

package team0301.ExportService.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "event")
public class EventXMLFormat {
	@JacksonXmlProperty(localName = "eventName")
	private String eventName;

	@JacksonXmlProperty(localName = "startDate")
	private String startDate;
	
	@JacksonXmlProperty(localName = "endDate")
	private String endDate;
	
	@JacksonXmlProperty(localName = "location")
	private String location;
	
	@JacksonXmlProperty(localName = "description")
	private String description;
	
	// Getters and setters

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}

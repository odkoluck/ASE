package team0301.testProducer.model;

public class TestProducerRequest {
	
	private String eventId;
	private String eventName;
	private String startDate;
	private String endDate;
    private String location;
    private String description;
    
	public TestProducerRequest(String eventId, String eventName, String startDate, String endDate, String location,
			String description) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.description = description;
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

package team0301.ExportService.scheme;

public class ResponseScheme {
	
	private String eventName;
	private String startDate;
	private String endDate;
    private String location;
    private String description;
    
    private String message;
    
    
	
	public ResponseScheme(String eventName, String startDate, String endDate, String location, String description) {
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.description = description;
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
	
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public static ResponseScheme getInstance(String eventName, String startDate,  String endDate, String location, String description) {
    	return new ResponseScheme(eventName,startDate, endDate, location, description );
    }
	
	public static ResponseScheme getInstance(String message) {
		ResponseScheme res = new ResponseScheme(null, null, null, null, null);
		res.setMessage(message);
    	return res;
    }
	

}

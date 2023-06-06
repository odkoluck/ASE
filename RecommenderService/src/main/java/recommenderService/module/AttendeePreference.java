package recommenderService.module;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendee_preferences")
public class AttendeePreference {
	
	@Id
	private String attendeeId;
    private String capacity;
    private String type;
    private String location;
    private String criteria;
    public List <String> bookmarkedEventIds = new ArrayList<>();
    
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

	public List <String> getBookmarkedEventIds() {
		return bookmarkedEventIds;
	}

	public void setBookmarkedEventIds(List <String> bookmarkedEventIds) {
		this.bookmarkedEventIds = bookmarkedEventIds;
	}

	public String getAttendeeId() {
		return attendeeId;
	}

	public void setAttendeeId(String attendeeId) {
		this.attendeeId = attendeeId;
	}
}

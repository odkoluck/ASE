package com.taggingService.Model;

import java.util.ArrayList; 
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tag {

	@Id
	private String id;
	
    private String name;
    private List<String> eventIds = new ArrayList<>();
    private List<String> attendeeIds = new ArrayList<>();

    public Tag(String name, List<String> eventIds, List<String> attendeeIds) {
        this.name = name;
        this.eventIds = eventIds;
        this.attendeeIds = attendeeIds;
    }
    public Tag() {
		// Default constructor
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }

    public List<String> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(List<String> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }
}

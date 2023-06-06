package team0301.ExportService.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="calenderExportService")
public class CalenderExportService {
	
	@Id
	private Integer userId;
	
	private List<Event> eventList = new ArrayList<>();
	
	public CalenderExportService() {
		this(0);
	}

	public CalenderExportService(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Event> getEventList() {
		return eventList;
	}
     

}

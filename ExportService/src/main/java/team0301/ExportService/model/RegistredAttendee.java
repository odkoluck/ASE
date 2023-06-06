package team0301.ExportService.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="registredAttendee")
public class RegistredAttendee {
	
	
	private String userId;
	private String registredEventId;
	
	
	
	public RegistredAttendee() {
		this("", "");
	}


	public RegistredAttendee(String userId, String registredEventId) {
		this.userId = userId;
		this.registredEventId = registredEventId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getRegistredEventId() {
		return registredEventId;
	}


	public void setRegistredEventId(String registredEventId) {
		this.registredEventId = registredEventId;
	}
	
	 

}

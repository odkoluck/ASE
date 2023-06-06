package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="registredAttendee")
public class RegistredAttendee {
	
	
	private Integer userId;
	private Integer registredEventId;
	
	
	
	public RegistredAttendee() {
		this(1,1);
	}
	

	public RegistredAttendee(Integer userId, Integer registredEventId) {
	
		this.userId = userId;
		this.registredEventId = registredEventId;
	}



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRegistredEventId() {
		return registredEventId;
	}

	public void setRegistredEventId(Integer registredEventId) {
		this.registredEventId = registredEventId;
	}
	

    

}

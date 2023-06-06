package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.demo.repository.EventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AnalyticsFeedbackRepository;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EventRepository.class)
public class AnalyticsServiceApplication {
	
	
    /*private static Set<String[]> users;
	private static Set<String[]> events;
	private static Set<String[]> feedbacks;
	
	private static Set<String[]> readCSV(String filePath) {
		String line = "";
		Set<String[]> result = new LinkedHashSet<>();
		try {
			// skip the first line (column names)
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			br.readLine();
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] data = line.split(",");
				result.add(data);
			}
		} catch (IOException e) {
			System.out.println("ERROR from readCSV: " + e.getMessage());
		}
		return result;
	}

	private static Map<String, Object> toJSON(Set<String[]> source, String[] keys) {
		Map<String, Object> jsonObj = new HashMap<>();
		ArrayList<Map<String, Object>> data = new ArrayList<>();

		for (String[] el : source) {
			Map<String, Object> childObj = new HashMap<>();
			for (int i = 0; i < keys.length; i++) {
				childObj.put(keys[i], el[i]);
			}
			data.add(childObj);
		}

		jsonObj.put("data", data);
		return jsonObj;
	}*/
	
	public static void main(String[] args) {
		
		// initialise DB connection
		// TODO: this should be fixed in the future
		//String relativePath = "C:\\Users\\odkol\\Documents\\UniWien\\ASE_23SS\\ASE_Team_0301\\implementation\\AnalyticsService\\src\\main\\java\\com\\example\\demo\\";
		//String relativePath = "./src/main/java/com/example/demo/";
		//System.out.println(System.getProperty("user.dir"));
		// fetch event from DB
		/*users = readCSV(relativePath + "db-user.csv");
		events = readCSV(relativePath + "db-event.csv");
		feedbacks = readCSV(relativePath + "db-feedback.csv");*/
		
		SpringApplication.run(AnalyticsServiceApplication.class, args);
	}
	
	/*@GetMapping("/reports/{id}")
	public Map<String, Object> getReport(@PathVariable("id") String eventId) {
		String[] keys =  new String[]{ "id", "name", "registered", "attended", "bookmarked" };
		
		Map<String, Object> jsonObj = new HashMap<>();
		String[] event = null;
		
		for (String[] e : events) {
			if (e[0].equals(eventId)) {
				event = e;
				break;
			}
		}
		
		if (event == null) {
			jsonObj.put("error", "Not found");
			return jsonObj;
		}
		
		for (int i = 0; i < keys.length; i++) {
			jsonObj.put(keys[i], event[i]);
		}

		return jsonObj;
	}
	
	@GetMapping("/reports")
	public Map<String, Object> getAllReports() {
		String[] keys =  new String[]{ "id", "name", "registered", "attended", "bookmarked" };
		return toJSON(events, keys);
	}
	
	// Feedback Analytics 
	
	@GetMapping("/feedback")
	public Map<String, Object> getAllFeedbacks() {
		String[] keys =  new String[]{ "id", "event_id", "location", "organization", "description_accuracy" };
		return toJSON(feedbacks, keys);
	}
	
	@GetMapping("/feedback/{id}")
	public Map<String, Object> getFeaadback(@PathVariable("id") String eventId) {
		String[] keys =  new String[]{ "id", "event_id", "location", "organization", "description_accuracy" };
		
		Map<String, Object> jsonObj = new HashMap<>();
		String[] event = null;
		
		for (String[] e : feedbacks) {
			if (e[1].equals(eventId)) {
				event = e;
				break;
			}
		}
		
		if (event == null) {
			jsonObj.put("error", "This event has not given Feedback ");
			return jsonObj;
		}
		
		for (int i = 0; i < keys.length; i++) {
			//System.out.println()
			jsonObj.put(keys[i], event[i]);
		}

		return jsonObj;
	}*/
	
	
	
	
	

	
	

}

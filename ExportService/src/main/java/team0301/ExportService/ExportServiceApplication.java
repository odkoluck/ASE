package team0301.ExportService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import team0301.ExportService.repository.CalendarExportRepository;


@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = CalendarExportRepository.class)
public class ExportServiceApplication {
	
	
	/*private static Set<String[]> readCSV(String filePath) {
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
		SpringApplication.run(ExportServiceApplication.class, args);
		//String relativePath = "./src/main/java/team0301/ExportService/";
		//events = readCSV(relativePath + "db-event.csv");
	}
	
	

}

package com.team0301.AttendanceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.team0301.AttendanceService.repository.AttendanceRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = AttendanceRepository.class)
public class AttendanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceServiceApplication.class, args);
	}

}

package com.example.demo;

import com.example.demo.SearchService.SearchCriteria;
import com.example.demo.SearchService.SearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@SpringBootApplication
public class SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}

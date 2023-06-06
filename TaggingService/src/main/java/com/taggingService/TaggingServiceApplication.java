package com.taggingService;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import com.taggingService.Service.TaggingService;

@SpringBootApplication
public class TaggingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaggingServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner initializeTags(TaggingService taggingService) {
        return args -> {
            taggingService.createInitialTags();
        };
    }
}
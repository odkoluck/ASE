package com.taggingService.Controller;

import com.taggingService.TaggingRabbitMQConfig;
import com.taggingService.DTO.TaggingResponse;
import com.taggingService.Model.Tag;
import com.taggingService.Service.TaggingService;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TaggingController {

    private final TaggingService taggingService;
    @Autowired
    RabbitTemplate template;
    
    @Autowired
    public TaggingController(TaggingService taggingService) {
        this.taggingService = taggingService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return taggingService.getAllTags();
    }

    @PostMapping("/{tagName}/events/{eventId}")
    public ResponseEntity<Tag> tagEvent(@PathVariable String tagName, @PathVariable String eventId) {
        Tag existingTag = taggingService.getTagByName(tagName);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingTag.getEventIds().contains(eventId)) {
            existingTag.getEventIds().add(eventId);
            taggingService.updateTag(existingTag);
        }

        return ResponseEntity.ok(existingTag);
    }

    @DeleteMapping("/{tagName}/events/{eventId}")
    public ResponseEntity<TaggingResponse> untagEvent(@PathVariable String tagName, @PathVariable String eventId) {
        Tag existingTag = taggingService.getTagByName(tagName);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
        }

        if (existingTag.getEventIds().contains(eventId)) {
            existingTag.getEventIds().remove(eventId);
            taggingService.updateTag(existingTag);
        }

        TaggingResponse response = new TaggingResponse();
        response.setMessage("Event untagged successfully.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{tagName}/attendees/{attendeeId}")
    public ResponseEntity<Tag> addAttendeeToTag(@PathVariable String tagName, @PathVariable String attendeeId) {
        Tag existingTag = taggingService.getTagByName(tagName);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingTag.getAttendeeIds().contains(attendeeId)) {
            existingTag.getAttendeeIds().add(attendeeId);
            taggingService.updateTag(existingTag);
        }

        return ResponseEntity.ok(existingTag);
    }
    
    @PostMapping("/{tagName}/{eventId}/{attendeeId}")
    public ResponseEntity<Tag> tagEventAndAddAttendee(@PathVariable String tagName, @PathVariable String eventId, @PathVariable String attendeeId) {
        Tag existingTag = taggingService.getTagByName(tagName);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingTag.getEventIds().contains(eventId)) {
            existingTag.getEventIds().add(eventId);
        }

        if (!existingTag.getAttendeeIds().contains(attendeeId)) {
            existingTag.getAttendeeIds().add(attendeeId);
        }
        
        List<String> message = new ArrayList<>();
        message.add(tagName);
        message.add(attendeeId);
        message.add(eventId);
        System.out.println("Message sent" + message);
        // Publish the message to RabbitMQ
        template.convertAndSend(TaggingRabbitMQConfig.EXCHANGE, TaggingRabbitMQConfig.ROUTING_KEY, message);
         taggingService.updateTag(existingTag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
       
    }   
}

package com.taggingService.Service;

import static com.taggingService.TaggingRabbitMQConfig.taggingQueue;

import java.util.Arrays; 
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taggingService.TaggingRabbitMQConfig;
import com.taggingService.Model.Tag;
import com.taggingService.Repository.TaggingRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TaggingService {

    private final TaggingRepository taggingRepository;
    private final RabbitAdmin rabbitAdmin;
    private final TaggingRabbitMQConfig rabbitMQConfig;

    @Autowired
    public TaggingService(TaggingRepository taggingRepository, RabbitAdmin rabbitAdmin, TaggingRabbitMQConfig rabbitMQConfig) {
        this.taggingRepository = taggingRepository;
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitMQConfig = rabbitMQConfig;
    }
    @PostConstruct
    public void initializeQueuesAndBindings() {
        rabbitAdmin.declareQueue(taggingQueue());
        rabbitAdmin.declareBinding(TaggingRabbitMQConfig.binding(taggingQueue(), TaggingRabbitMQConfig.exchange()));
    }
   
    public void createInitialTags() {
        List<String> tagNames = Arrays.asList("Music", "Sports", "Technology", "Food", "Art", "Fitness", "Networking",
                "Education", "Travel", "Fashion");

        for (String tagName : tagNames) {
            if (!taggingRepository.existsByName(tagName)) {
            	Tag tag = new Tag();
                tag.setName(tagName);
                taggingRepository.save(tag);
            }
        }
    }

    public List<Tag> getAllTags() {
        return taggingRepository.findAll();
    }

    public void tagEvent(String tagName, String eventId) {
        Tag tag = taggingRepository.findByName(tagName);
        if (tag != null) {
            if (!tag.getEventIds().contains(eventId)) {
                tag.getEventIds().add(eventId);
                taggingRepository.save(tag);
            }
        } else {
            // Handle case when tag is not found
        }
    }


    public void untagEvent(String tagId, String eventId) {
        Tag tag = taggingRepository.findById(tagId).orElseThrow();
        if (tag.getEventIds().contains(eventId)) {
            tag.getEventIds().remove(eventId);
            taggingRepository.save(tag);
        }
    }

    public Tag getTagByName(String tagName) {
        return taggingRepository.findByName(tagName);
    }

    public Tag getTagById(String tagId) {
        return taggingRepository.findById(tagId).orElse(null);
    }

    public void updateTag(Tag tag) {
        taggingRepository.save(tag);
    }
}

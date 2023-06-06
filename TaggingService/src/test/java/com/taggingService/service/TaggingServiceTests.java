package com.taggingService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.taggingService.Model.Tag;
import com.taggingService.Repository.TaggingRepository;
import com.taggingService.Service.TaggingService;

public class TaggingServiceTests {

    @Mock
    private TaggingRepository taggingRepository;

    @InjectMocks
    private TaggingService taggingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateInitialTags() {
        // Mocking the repository behavior
        when(taggingRepository.existsByName(anyString())).thenReturn(false);

        // Test data
        List<String> tagNames = Arrays.asList("Music", "Sports", "Technology", "Food", "Art", "Fitness", "Networking", "Education", "Travel", "Fashion");

        // Call the method
        taggingService.createInitialTags();

        // Verify the repository interactions
        verify(taggingRepository, times(tagNames.size())).save(any(Tag.class));
    }

    @Test
    public void testGetAllTags() {
        // Mocking the repository behavior
        List<Tag> mockTags = Arrays.asList(new Tag(), new Tag(), new Tag());
        when(taggingRepository.findAll()).thenReturn(mockTags);

        // Call the method
        List<Tag> result = taggingService.getAllTags();

        // Verify the result
        assertEquals(mockTags.size(), result.size());
    }

  /*  @Test
    public void testTagEvent() {
        // Test data
    	ObjectId tagId = new ObjectId();
        String eventId = "event1";
        Tag mockTag = new Tag();
        mockTag.setEventIds(new ArrayList<>());

        // Mocking the repository behavior
        when(taggingRepository.findById(tagId)).thenReturn(Optional.of(mockTag));

        // Call the method
        taggingService.tagEvent(tagId, eventId);

        // Verify the repository interactions
        verify(taggingRepository).save(mockTag);
        assertTrue(mockTag.getEventIds().contains(eventId));
    }

    @Test
    public void testUntagEvent() {
        // Test data
    	ObjectId tagId = new ObjectId();
        String eventId = "event1";
        Tag mockTag = new Tag();
        mockTag.setEventIds(new ArrayList<>(Arrays.asList(eventId)));

        // Mocking the repository behavior
        when(taggingRepository.findById(tagId)).thenReturn(Optional.of(mockTag));

        // Call the method
        taggingService.untagEvent(tagId, eventId);

        // Verify the repository interactions
        verify(taggingRepository).save(mockTag);
        assertFalse(mockTag.getEventIds().contains(eventId));
    }*/
}

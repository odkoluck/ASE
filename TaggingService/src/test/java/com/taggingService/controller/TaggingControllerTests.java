package com.taggingService.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.taggingService.Model.Tag;
import com.taggingService.Service.TaggingService;

@SpringBootTest
@AutoConfigureMockMvc
public class TaggingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaggingService taggingService;
/*
    @Test
    public void testGetAllTags() throws Exception {
        List<Tag> tags = Arrays.asList(
            new Tag( "Music", Arrays.asList("event1", "event2"), Arrays.asList("attendee1", "attendee2")),
            new Tag("Sports", Arrays.asList("event3", "event4"), Arrays.asList("attendee3", "attendee4")),
            new Tag("Technology", Arrays.asList("event5", "event6"), Arrays.asList("attendee5", "attendee6"))
        );

        given(taggingService.getAllTags()).willReturn(tags);

        mockMvc.perform(get("/tags"))
               .andExpect(status().isOk())
               .andExpect(content().json("[{name: 'Music'},{name: 'Sports'},{name: 'Technology'}]"));
    }*/
}

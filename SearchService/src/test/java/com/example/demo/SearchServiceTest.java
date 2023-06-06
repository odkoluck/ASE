/*package com.example.demo;

import com.example.demo.SearchService.SearchCriteria;
import com.example.demo.SearchService.SearchRepository;
import com.example.demo.SearchService.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SearchServiceTest {

    @Autowired
    private SearchRepository searchRepository;
    private SearchService searchService;
    @Test
    public void testSearchEvents() {
        // Create and save some test data
        SearchCriteria event1 = new SearchCriteria("12345", LocalDate.now(), "Event 1", "Location 1", LocalTime.of(10, 0), 100, "Type 1", "Description 1", "Organizer 1");
        SearchCriteria event2 = new SearchCriteria("123458", LocalDate.now(), "Event 2", "Location 2", LocalTime.of(15, 0), 200, "Type 2", "Description 2", "Organizer 2");
        searchRepository.save(event1);
        searchRepository.save(event2);

        // Perform the search
        SearchCriteria searchCriteria = new SearchCriteria(null, LocalDate.now(), "Event 1", null, null, null, null, null, null);
        List<SearchCriteria> results = searchService.searchEvents(searchCriteria);

        // Assert the expected results
        assertEquals(1, results.size());
        assertEquals(event1.getEventName(), results.get(0).getEventName());
    }
}
*/

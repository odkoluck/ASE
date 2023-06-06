package com.example.demo.SearchService;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events/search")
@CrossOrigin
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping()
    public List<SearchCriteria> searchEvents(@RequestParam(required = false) String eventId,
                                             @RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) String endDate,
                                             @RequestParam(required = false) String eventName,
                                             @RequestParam(required = false) String location,
                                             @RequestParam(required = false) String startTime,
                                             @RequestParam(required = false) Integer capacity,
                                             @RequestParam(required = false) String type,
                                             @RequestParam(required = false) String description,
                                             @RequestParam(required = false) String emailAddress) {
        // Create a new instance of SearchCriteria with the provided parameters
        SearchCriteria searchCriteria = new SearchCriteria(eventId, startDate, endDate, eventName, location, startTime, capacity, type, description, emailAddress);

        // Call the searchEvents method in the SearchService and pass the search criteria
        return searchService.searchEvents(searchCriteria);
    }
}

package com.example.demo.SearchService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
public class EventMessageConsumer {

    private final SearchService searchService;

    public EventMessageConsumer(SearchService searchService) {
        this.searchService = searchService;
    }



    //Create
    @RabbitListener(queues = "searchEventQueue")
    public void receiveMessage(Map<String, Object> eventData) {
        System.out.println(eventData.get("id"));
        System.out.println(eventData.get("startDate"));
        System.out.println(eventData.get("endDate"));
        System.out.println(eventData.get("eventName"));
        System.out.println(eventData.get("startTime"));
        System.out.println(eventData.get("capacity"));
        System.out.println(eventData.get("type"));
        System.out.println(eventData.get("description"));
        System.out.println(eventData.get("emailAddress"));
        SearchCriteria searchCriteria = convertToSearchCriteria(eventData);
        searchService.insertEvent(searchCriteria);
    }

    //Converting
    private SearchCriteria convertToSearchCriteria(Map<String, Object> eventData) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setId((String) eventData.get("id"));
        searchCriteria.setStartDate((String) eventData.get("startDate"));
        searchCriteria.setEndDate((String) eventData.get("endDate"));
        searchCriteria.setEventName((String) eventData.get("eventName"));
        searchCriteria.setLocation((String) eventData.get("location"));
        searchCriteria.setStartTime((String) eventData.get("startTime"));
        searchCriteria.setCapacity((Integer) eventData.get("capacity"));
        searchCriteria.setType((String) eventData.get("type"));
        searchCriteria.setDescription((String) eventData.get("description"));
        searchCriteria.setEmailAddress((String) eventData.get("emailAddress"));
        return searchCriteria;
    }

    //Delete


    @RabbitListener(queues = "deleteEventQueue")
    public void deleteEvent(Map<String, Object> eventData) {
        String id = (String) eventData.get("id");
        searchService.deleteEvent(id);
    }

     //Update
    @RabbitListener(queues = "updateEventQueue")
    public void Message(Map<String, Object> eventData) {
        SearchCriteria searchCriteria = convertToSearchCriteria(eventData);
        searchService.updateEvent(searchCriteria);
    }
}


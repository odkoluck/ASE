package recommenderService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import recommenderService.module.AttendeePreference;
import recommenderService.service.RecommenderService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class EventMessageConsumer {

    private final RecommenderService recommenderService;

    public EventMessageConsumer(RecommenderService recommenderService ) {
        this.recommenderService  = recommenderService ;
    }

    @RabbitListener(queues = "searchEventQueue")
    public void receiveMessage(String id, String startDate, String endDate, String eventName, 
    		String startTime, Integer capacity, String type, String description, String emailAddress) {
    	  // Retrieve all attendees from the AttendanceService or data source
        List<AttendeePreference> attendeePreferences = recommenderService.getAllAttendeePreferences();
        String capacityAsString = String.valueOf(capacity);
        // Iterate over attendee preferences and check if they match the event details
        for (AttendeePreference attendeePreference : attendeePreferences) {
            if (eventMatchesPreferences(capacityAsString, type, description, attendeePreference)) {
                // Event matches attendee preference
                // Send notification or recommendation to the attendee
                recommenderService.sendNotification(eventName, attendeePreference.getAttendeeId());
            }
        }
    }

    private boolean eventMatchesPreferences(String capacity, String type, String location,  AttendeePreference attendeePreferences) {
       
    	  boolean preferencesMatch =  capacity.equals(attendeePreferences.getCapacity())
                && type.equals(attendeePreferences.getType())
                && location.equals(attendeePreferences.getLocation());
    	  
    	 // boolean similarToBookmarked = attendeePreferences.bookmarkedEventIds.stream()
    		 //       .map(eventService::getEventById)
    		   //     .anyMatch(event -> areEventsSimilar(startDate, endDate, type, event));

    	    // Return true if either the preferences match or the event is similar to a bookmarked event
    	 //   return preferencesMatch || similarToBookmarked;
    	  return preferencesMatch;
    	}

    	/*private boolean areEventsSimilar(String capacity, String type, String location, Event bookmarkedEvent) {
    	    return capacity.equals(bookmarkedEvent.getcapacity()) &&
    	    		type.equals(bookmarkedEvent.gettype()) &&
    	    		location.equals(bookmarkedEvent.getlocation());
    	}*/
    }



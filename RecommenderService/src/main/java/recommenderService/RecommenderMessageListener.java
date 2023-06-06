package recommenderService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import recommenderService.service.RecommenderService;

@Component
public class RecommenderMessageListener {
    private final RecommenderService recommenderService;

    @Autowired
    public RecommenderMessageListener(RecommenderService recommenderService) {
        this.recommenderService = recommenderService;
    }


@RabbitListener(queues = "Bookmarking_Queue")
public void receiveMessage(String message) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        
        String eventId = jsonNode.get("eventId").asText();
        String attendeeId = jsonNode.get("attendeeId").asText();

        recommenderService.updateRecommendations(attendeeId, eventId);
        System.out.println("Message received: " + message + " Attendee:" + attendeeId + " Event:" + eventId);
    } catch (Exception e) {
        // Handle the exception
    }
}

@RabbitListener(queues = "tagging_Queue")
public void receiveMessage1(String message) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        
        String tagName = jsonNode.get("tagName").asText();
        String eventId = jsonNode.get("eventId").asText();
        String attendeeId = jsonNode.get("attendeeId").asText();
        System.out.println("Message received: " + tagName + message + " Attendee:" + attendeeId + " Event:" + eventId);
    } catch (Exception e) {
    	System.out.println("Message not received: ");
    }
}
@RabbitListener(queues = "eventQueue")
public void receiveMessage2(String message) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        
        String tagName = jsonNode.get("tagName").asText();
        String eventId = jsonNode.get("eventId").asText();
        String attendeeId = jsonNode.get("attendeeId").asText();
        System.out.println("Message received: " + tagName + message + " Attendee:" + attendeeId + " Event:" + eventId);
    } catch (Exception e) {
    	System.out.println("Message not received: ");
    }
}
}
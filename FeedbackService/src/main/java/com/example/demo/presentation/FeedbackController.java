package com.example.demo.presentation;

import com.example.demo.domain.Feedback;
import com.example.demo.application.FeedbackService;
import com.example.demo.infrastructure.RabbitMQConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.demo.infrastructure.RabbitMQConfig.*;

@RestController
@RequestMapping(path="/feedbacks")
@CrossOrigin
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final RabbitAdmin rabbitAdmin;
    private final RabbitTemplate template;


    public FeedbackController(FeedbackService feedbackService, RabbitAdmin rabbitAdmin, RabbitTemplate template) {
        this.feedbackService = feedbackService;
        this.rabbitAdmin = rabbitAdmin;
        this.template = template;
    }

    @PostConstruct
    public void initializeQueuesAndBindings() {
        //Feedbacks
        // Create
        rabbitAdmin.declareQueue(RabbitMQConfig.feedbackQueue());
        rabbitAdmin.declareBinding(RabbitMQConfig.bindingFBQueue(exchange()));

        //Delete
        rabbitAdmin.declareQueue(RabbitMQConfig.feedbackDeleteQueue());
        rabbitAdmin.declareBinding(RabbitMQConfig.deleteFeedbackBinding(exchange()));

        //Update
        rabbitAdmin.declareQueue(RabbitMQConfig.feedbackUpdateQueue());
        rabbitAdmin.declareBinding(RabbitMQConfig.updateFeedback(exchange()));

        //Login Service for UserID
        rabbitAdmin.declareQueue(RabbitMQConfig.feedback_login_Queue());
        rabbitAdmin.declareBinding(RabbitMQConfig.bindingFBLoginQueue(RabbitMQConfig.exchange_login()));

        //Event Service for EventID
        rabbitAdmin.declareQueue(RabbitMQConfig.feedback_event_Queue());
        rabbitAdmin.declareBinding(RabbitMQConfig.bindingFBEventQueue(RabbitMQConfig.exchange_event()));
       }

    @GetMapping()
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedbacks();
    }


    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable String id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/event/{eventId}")
    public List<Feedback> getFeedbackByEventId(@PathVariable String eventId) {
        return feedbackService.getFeedbackByEventId(eventId);
    }

    @GetMapping("/user/{userId}")
    public List<Feedback> getFeedbackByUserId(@PathVariable String userId) {
        return feedbackService.getFeedbackByUserId(userId);
    }

    @PostMapping(value = "/{eventId}/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createFeedback(@PathVariable String eventId, @RequestBody Feedback feedback, @RequestHeader String tokenId) {
        try {

        if (tokenId == null || tokenId.isEmpty()) {
            String errorMessage = "TokenId is missing! Please log in first and provide your token.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
            //System.out.println(tokenId);

            // This checks the input parameters, and the rating has to be between 1 and 5
            FeedbackService.inputValidation(eventId, feedback);

            String userID = getUserID(tokenId);
            //System.out.println(userID);
            String eventIdChecked = checkEventId(eventId);
            Feedback newFeedback = feedbackService.createFeedback(eventIdChecked, userID, feedback);
            Map<String, Object> response = new HashMap<>();
            response.put("feedback", newFeedback);
            response.put("message", "Successfully created feedback!");
            template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEYS, newFeedback);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String errorMessage = "Feedback was not created: " + e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    public String getUserID(String tokenId) throws Exception {
        MessageProperties messageProperties = new MessageProperties();
        Message message = template.getMessageConverter().toMessage(tokenId, messageProperties);
        Message response = template.sendAndReceive("FB_Login_Queue", message);
        String userId;
        if (response != null && response.getBody() != null) {
            userId = new String(response.getBody()).replaceAll("\"","");
        } else {
            throw new Exception("Error with validation. Check if your tokenId is valid!");
        }
        return userId;
    }

    public String checkEventId(String eventId) throws Exception {
        MessageProperties messageProperties = new MessageProperties();
        Message message = template.getMessageConverter().toMessage(eventId, messageProperties);
        Message response = template.sendAndReceive("FB_Event_Queue", message);
        String existingEventId;
        System.out.println(eventId);
        System.out.println(response);
        if (response != null && response.getBody() != null) {
            existingEventId = new String(response.getBody()).replaceAll("\"","");
            System.out.println(existingEventId);
        } else {
            throw new Exception("Error. You can only add Feedbacks to existing events!");
        }
        return existingEventId;
    }


    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteFeedback(@PathVariable String id, @RequestHeader String tokenId) throws Exception {
        try {
            String userId = getUserID(tokenId);

            // Check if the feedback exists
            Feedback existingFeedback = feedbackService.getFeedbackById(id);
            if (existingFeedback == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback with ID " + id + " not found.");
            }

            if (Objects.equals(existingFeedback.getUserId(), userId)) {
                feedbackService.deleteFeedback(id);
                template.convertAndSend(RabbitMQConfig.EXCHANGE, "feedback.delete", id);
                return ResponseEntity.ok("Successfully deleted feedback with ID " + id + ".");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You can only delete your own Feedbacks! Try again!");
            }


        } catch (Exception e) {
            String errorMessage = "Feedback was not deleted: " + e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }



    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateFeedback(@PathVariable String id, @RequestBody Feedback feedback, @RequestHeader String tokenId) {
        try {


            if (tokenId == null || tokenId.isEmpty()) {
                String errorMessage = "TokenId is missing! Please log in first and provide your token.";
                return ResponseEntity.badRequest().body(errorMessage);
            }

            Feedback existingFeedback = feedbackService.getFeedbackById(id);
            if (existingFeedback == null) {
                String errorMessage = "Feedback with ID " + id + " does not exist.";
                return ResponseEntity.badRequest().body(errorMessage);
            }

            // This checks the input parameters, and the rating has to be between 1 and 5
            FeedbackService.inputValidation(existingFeedback.getEventId(), feedback);
            String userID = getUserID(tokenId);

            if (Objects.equals(existingFeedback.getUserId(), userID)) {
                Feedback updatedFeedback = feedbackService.updateFeedback(id, userID, feedback);
                if (updatedFeedback != null) {
                    template.convertAndSend(RabbitMQConfig.EXCHANGE, "feedback.update", updatedFeedback);
                    Map<String, Object> response = new HashMap<>();
                    response.put("feedback", updatedFeedback);
                    response.put("message", "Successfully updated feedback!");
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update feedback.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You can only edit your own Feedbacks! Try again!");
            }



        } catch (Exception e) {
            String errorMessage = "Feedback was not updated: " + e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

}




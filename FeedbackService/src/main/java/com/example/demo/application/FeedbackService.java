package com.example.demo.application;
//import com.example.demo.LoginService;
import com.example.demo.domain.Feedback;
import com.example.demo.presentation.FeedbackController;
import com.example.demo.domain.FeedbackRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private final FeedbackRepository repository;
    @Autowired
    private final RabbitTemplate rabbitTemplate;
    public FeedbackService(FeedbackRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }

    public Feedback getFeedbackById(String id) {
        return repository.findById(id).orElse(null);
       }

    public List<Feedback> getFeedbackByEventId(String eventId) {
        return repository.findByEventId(eventId);
    }

    public Feedback createFeedback(String eventID, String userID, Feedback feedback) throws IllegalArgumentException {

        inputValidation(eventID, feedback);
        feedback.setUserId(userID);
        feedback.setEventId(eventID);
        repository.insert(feedback);
        return feedback;
    }

    public static void inputValidation(@RequestParam String eventId, @RequestBody Feedback feedback) {
        if (feedback.getLocation() == 0 || feedback.getAccuracy() == 0
                || feedback.getPerformance() == 0 || feedback.getRating() == 0 || feedback.getDescription() == null) {
            throw new IllegalArgumentException("Missing feedback parameter");
        }

        if (eventId == null) {
            throw new IllegalArgumentException("Missing eventId");
        }

        if (feedback.getPerformance() < 1 || feedback.getPerformance() > 5) {
            throw new IllegalArgumentException("Performance Rating value must be between 1 and 5");
        }

        if (feedback.getLocation() < 1 || feedback.getLocation() > 5) {
            throw new IllegalArgumentException("Location Rating value must be between 1 and 5");
        }

        if (feedback.getAccuracy() < 1 || feedback.getAccuracy() > 5) {
            throw new IllegalArgumentException("Accuracy Rating value must be between 1 and 5");
        }
    }

    public void deleteFeedback(String id) {
        repository.deleteById(id);
    }

    public List<Feedback> getFeedbackByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Feedback updateFeedback(String id, String userID, Feedback feedback) {
        Feedback existingFeedback = repository.findByFeedbackId(id);
        existingFeedback.setUserId(userID);
        existingFeedback.setAccuracy(feedback.getAccuracy());
        existingFeedback.setLocation(feedback.getLocation());
        existingFeedback.setDescription(feedback.getDescription());
        existingFeedback.setPerformance(feedback.getPerformance());
        return repository.save(existingFeedback);
    }
}


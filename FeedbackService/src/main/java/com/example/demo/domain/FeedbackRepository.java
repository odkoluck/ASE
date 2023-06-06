package com.example.demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByEventId(String eventId);
    List<Feedback> findByUserId(String userId);
    Feedback findByFeedbackId(String feedbackId);
}


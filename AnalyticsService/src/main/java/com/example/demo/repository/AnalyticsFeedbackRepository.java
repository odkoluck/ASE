package com.example.demo.repository;

import com.example.demo.model.AnalyticsFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Feedback;


public interface AnalyticsFeedbackRepository extends MongoRepository <AnalyticsFeedback, String> {

}

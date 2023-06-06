package com.example.demo.application;

import com.example.demo.infrastructure.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FeedbackProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.Feedback_QUEUE_Create, message);
    }
}

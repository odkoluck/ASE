package com.example.demo;

import com.example.demo.application.FeedbackProducer;
import com.example.demo.infrastructure.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

        import static org.mockito.Mockito.verify;

public class FeedbackProducerTest {

    private FeedbackProducer feedbackProducer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        feedbackProducer = new FeedbackProducer(rabbitTemplate);
    }

    @Test
    public void testSendMessage() {
        String message = "Test message";
        feedbackProducer.sendMessage(message);
        verify(rabbitTemplate).convertAndSend(RabbitMQConfig.Feedback_QUEUE_Create, message);
    }
}


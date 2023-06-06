package com.example.Event;

import com.example.Event.communicationConfiguration.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(MessagingConfig.QUEUE, message);
    }
}

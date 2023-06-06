package com.example.Event;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.NoSuchElementException;
import java.util.Optional;
@Configuration
@AllArgsConstructor
public class EventListener {
    private final RabbitTemplate rabbitTemplate;
    private final EventInventoryService eventInventoryService;
    private final EventRepository eventRepository;

    @RabbitListener(queues = "FB_Event_Queue")
    public String getId(@Payload String eventId) {
        try {
            System.out.println(eventId);
            Optional<Event> eventToFind = eventRepository.findById(eventId);
            System.out.println(eventToFind);
            if (eventToFind.isPresent()) {
                System.out.println(eventId);
                return eventId;
            } else {
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("NO EVENT WITH THAT ID FOUND");
            return null;
        }
    }
}






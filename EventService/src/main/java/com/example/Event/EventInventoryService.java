package com.example.Event;

import com.example.Event.communicationConfiguration.MessagingConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.example.Event.communicationConfiguration.MessagingConfig.*;

@Service
public class EventInventoryService {

    private final EventRepository eventRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitAdmin rabbitAdmin;

    public EventInventoryService(EventRepository eventRepository, RabbitTemplate rabbitTemplate, RabbitAdmin rabbitAdmin) {
        this.eventRepository = eventRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitAdmin = rabbitAdmin;
    }

    @PostConstruct
    public void initializeQueuesAndBindings() {
        rabbitAdmin.declareQueue(eventQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(eventQueue(), MessagingConfig.eventExchange()));
        rabbitAdmin.declareQueue(roleQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(roleQueue(), MessagingConfig.roleExchange()));
        rabbitAdmin.declareQueue(MessagingConfig.searchEventQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(MessagingConfig.searchEventQueue(), MessagingConfig.searchEventExchange()));
        rabbitAdmin.declareQueue(MessagingConfig.deleteEventQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(deleteEventQueue(), MessagingConfig.deleteEventExchange()));
        rabbitAdmin.declareQueue(updateEventQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(updateEventQueue(), MessagingConfig.updateEventExchange()));
        rabbitAdmin.declareQueue(emailQueue());
        rabbitAdmin.declareBinding(MessagingConfig.binding(emailQueue(), MessagingConfig.emailExchange()));
    }

    public String getEmailAddress(String tokenId) throws ManageEventException {
        MessageProperties messageProperties = new MessageProperties();
        Message message = rabbitTemplate.getMessageConverter().toMessage(tokenId, messageProperties);
        Message response = rabbitTemplate.sendAndReceive("emailQueue", message);
        String emailAddress;
        if (response != null && response.getBody() != null) {
            emailAddress = new String(response.getBody()).replaceAll("\"", "");
        } else {
            throw new ManageEventException("ERROR_RECEIVING_EMAILADDRESS");
        }
        return emailAddress;
    }


    public String checkRole(String tokenId) throws ManageEventException {
        MessageProperties messageProperties = new MessageProperties();
        Message message = rabbitTemplate.getMessageConverter().toMessage(tokenId, messageProperties);
        Message response = rabbitTemplate.sendAndReceive("roleQueue", message);
        String userRole;
        if (response != null && response.getBody() != null) {
            userRole = new String(response.getBody()).replaceAll("\"", "");
        } else {
            throw new ManageEventException("ERROR_RECEIVING_USERROLE");
        }
        return userRole;
    }

    public List<Event> getAllEvent() throws ManageEventException {
        List<Event> allEvents = eventRepository.findAll();
        if (!allEvents.isEmpty()) {
            return allEvents;
        } else {
            throw new ManageEventException("NO EVENTS FOUND");
        }
    }

    public List<Event> getMyEvents(String tokenId) throws ManageEventException {
        String myEmailAddress = getEmailAddress(tokenId);
        List<Event> myEvents = eventRepository.findByEmailAddress(myEmailAddress);
        if (!myEvents.isEmpty()) {
            return myEvents;
        } else {
            throw new ManageEventException("NO EVENTS FOUND");
        }
    }

    public Event createEvent(Event event, String tokenId) throws ManageEventException {
        if (event.getStartDate().isEmpty() ||
                event.getEndDate().isEmpty() ||
                event.getEventName().isEmpty() ||
                event.getLocation().isEmpty() ||
                event.getStartTime().isEmpty() ||
                Objects.isNull(event.getCapacity()) ||
                event.getType().isEmpty() ||
                event.getDescription().isEmpty()) {
            throw new ManageEventException("FAILED TO CREATE EVENT, NOT ALL DETAILS PROVIDED");
        } else {
            String userRole = checkRole(tokenId);
            if (userRole.equals("ADMINISTRATOR") || userRole.equals("ORGANIZER")) {
                String emailAddress = getEmailAddress(tokenId);
                event.setEmailAddress(emailAddress);
                eventRepository.save(event);
                rabbitTemplate.convertAndSend(MessagingConfig.SEARCH_EVENT_EXCHANGE, MessagingConfig.SEARCH_EVENT_ROUTING_KEY, event);
                return event;
            } else {
                throw new ManageEventException("NO PERMISSION TO PERFORM THIS ACTION");
            }
        }
    }

    public ResponseEntity<?> deleteEvent(String id, String tokenId) throws ManageEventException {
        String myEmailAddress = getEmailAddress(tokenId);
        String userRole = checkRole(tokenId);
        Optional<Event> eventToFind = eventRepository.findById(id);
        if (userRole.equals("ADMINISTRATOR") || userRole.equals("ORGANIZER")) {
            if (eventToFind.isPresent()) {
                Event eventToDelete = eventToFind.get();
                if (myEmailAddress.equals(eventToDelete.getEmailAddress())) {
                    rabbitTemplate.convertAndSend(MessagingConfig.DELETE_EVENT_EXCHANGE, MessagingConfig.DELETE_EVENT_ROUTING_KEY, eventToDelete);
                    eventRepository.delete(eventToDelete);
                    return ResponseEntity.ok("Event has been successful deleted");
                } else {
                    throw new ManageEventException("NO PERMISSION TO DELETE THIS EVENT");
                }
            } else {
                throw new ManageEventException("EVENT DOES NOT EXIST");
            }
        } else {
            throw new ManageEventException("NO PERMISSION TO DELETE EVENTS");
        }
    }

    public Event updateEvent(Event event, String tokenId) throws ManageEventException {
        String userRole = checkRole(tokenId);
        if (userRole.equals("ORGANIZER") || userRole.equals("ADMINISTRATOR")) {
            Event savedEvent = eventRepository.findById(event.getId()).orElseThrow(() -> new ManageEventException("CAN´T FIND EVENT TO UPDATE"));
            if (!event.getStartDate().isEmpty()) {
                savedEvent.setStartDate(event.getStartDate());
            }
            if (!event.getEndDate().isEmpty()) {
                savedEvent.setEndDate(event.getEndDate());
            }
            if (!event.getEventName().isEmpty()) {
                savedEvent.setEventName(event.getEventName());
            }
            if (!event.getLocation().isEmpty()) {
                savedEvent.setLocation(event.getLocation());
            }
            if (!event.getStartTime().isEmpty()) {
                savedEvent.setStartTime(event.getStartTime());
            }
            if (!Objects.isNull(event.getCapacity())) {
                savedEvent.setCapacity(event.getCapacity());
            }
            if (!event.getType().isEmpty()) {
                savedEvent.setType(event.getType());
            }
            if (!event.getDescription().isEmpty()) {
                savedEvent.setDescription(event.getDescription());
            }
            rabbitTemplate.convertAndSend(MessagingConfig.UPDATE_EVENT_EXCHANGE, MessagingConfig.UPDATE_EVENT_ROUTING_KEY, savedEvent);
            return eventRepository.save(savedEvent);
        } else {
            throw new ManageEventException("NO PERMISSION TO UPDATE EVENTS");
        }
    }
}


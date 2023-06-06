package com.example.Event;

//import com.example.Event.communicationConfiguration.MessagingConfig;
import com.example.Event.communicationConfiguration.MessagingConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {

    private final RabbitTemplate rabbitTemplate;
    private final EventInventoryService eventInventoryService;
    private final EventRepository eventRepository;

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event, @RequestHeader String tokenId) throws ManageEventException {
        return eventInventoryService.createEvent(event, tokenId);

    }

    @GetMapping()
    public List<Event> showAllEvents() throws ManageEventException {
        return eventInventoryService.getAllEvent();
    }

    @GetMapping("/myEvents")
    public List<Event> showMyEvents(@RequestHeader String tokenId) throws ManageEventException {
        return eventInventoryService.getMyEvents(tokenId);
    }

    @DeleteMapping("/myEvents/{eventId}")
    public ResponseEntity<?> deleteEvent(@RequestHeader String tokenId, @PathVariable String eventId) throws ManageEventException {
        return eventInventoryService.deleteEvent(eventId, tokenId);
    }

    @PutMapping("/myEvents/{eventId}")
    public Event updateEvent(@RequestBody Event event, @RequestHeader String tokenId, @PathVariable String eventId) throws ManageEventException {
        String updaterEmail = eventInventoryService.getEmailAddress(tokenId);
        if (updaterEmail.equals(event.getEmailAddress())) {
            return eventInventoryService.updateEvent(event, tokenId);
        } else {
            throw new ManageEventException("ORGANIZER EMAIL ADDRESS DOES NOT MATCH");
        }
    }
}


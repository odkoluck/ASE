package bookmarkingService.controller;

import bookmarkingService.BookmarkingRabbitMQConfig;
import bookmarkingService.service.BookmarkingService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkingController {
    private final BookmarkingService bookmarkingService;
    
    @Autowired
    RabbitTemplate template;

    public BookmarkingController(BookmarkingService bookmarkingService) {
        this.bookmarkingService = bookmarkingService;
    }

    @PostMapping("/bookmark")
    public ResponseEntity<Void> bookmarkEvent(@RequestParam("attendeeId") String attendeeId, @RequestParam("eventId") String eventId) {
        bookmarkingService.bookmarkEvent(attendeeId, eventId);
        
        List<Object> message = new ArrayList<>();
        message.add(attendeeId);
        message.add(eventId);

        // Publish the message to RabbitMQ
        template.convertAndSend(BookmarkingRabbitMQConfig.EXCHANGE, BookmarkingRabbitMQConfig.ROUTING_KEY, message);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/unbookmark")
    public ResponseEntity<Void> unbookmarkEvent(@RequestParam String attendeeId, @RequestParam String eventId) {
        bookmarkingService.unbookmarkEvent(attendeeId, eventId);
        template.convertAndSend(BookmarkingRabbitMQConfig.EXCHANGE, BookmarkingRabbitMQConfig.ROUTING_KEY, eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/bookmarkedEventIds/{attendeeId}")
    public ResponseEntity<List<String>> getBookmarkedEventIds(@PathVariable String attendeeId) {
        List<String> bookmarkedEventIds = bookmarkingService.getBookmarkedEventIds(attendeeId);
        return ResponseEntity.ok(bookmarkedEventIds);
    }

    @GetMapping("/isEventBookmarked")
    public ResponseEntity<Boolean> isEventBookmarked(@RequestParam String attendeeId, @RequestParam String eventId) {
        boolean isBookmarked = bookmarkingService.isEventBookmarked(attendeeId, eventId);
        return ResponseEntity.ok(isBookmarked);
    }
}

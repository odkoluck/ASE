package bookmarkingService.service;

import java.util.UUID;  
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookmarkingService.model.Bookmark;
import bookmarkingService.repository.BookmarkingRepository;
import jakarta.annotation.PostConstruct;
import bookmarkingService.BookmarkingRabbitMQConfig;
import static bookmarkingService.BookmarkingRabbitMQConfig.bookmarkingQueue;

@Service
public class BookmarkingService {
    private final BookmarkingRepository bookmarkingRepository;
    private final RabbitAdmin rabbitAdmin;
    private final BookmarkingRabbitMQConfig rabbitMQConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BookmarkingService(BookmarkingRepository bookmarkingRepository, RabbitAdmin rabbitAdmin, BookmarkingRabbitMQConfig rabbitMQConfig) {
        this.bookmarkingRepository = bookmarkingRepository;
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitMQConfig = rabbitMQConfig;
    }

    @PostConstruct
    public void initializeQueuesAndBindings() {
        rabbitAdmin.declareQueue(bookmarkingQueue());
        rabbitAdmin.declareBinding(BookmarkingRabbitMQConfig.binding(bookmarkingQueue(), BookmarkingRabbitMQConfig.exchange()));
    }
   

   
    public void bookmarkEvent(String attendeeId, String eventId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setAttendeeId(attendeeId);
        bookmark.setEventId(eventId);
        bookmarkingRepository.save(bookmark);
        rabbitTemplate.convertAndSend(BookmarkingRabbitMQConfig.EXCHANGE, BookmarkingRabbitMQConfig.ROUTING_KEY, bookmark);
    }

    public void unbookmarkEvent(String attendeeId, String eventId) {
        Bookmark bookmark = bookmarkingRepository.findByAttendeeIdAndEventId(attendeeId, eventId);
        if (bookmark != null) {
            bookmarkingRepository.delete(bookmark);
        }
    }

    public List<String> getBookmarkedEventIds(String attendeeId) {
        List<Bookmark> bookmarks = bookmarkingRepository.findByAttendeeId(attendeeId);
        return bookmarks.stream().map(Bookmark::getEventId).collect(Collectors.toList());
    }

    public boolean isEventBookmarked(String attendeeId, String eventId) {
        return bookmarkingRepository.findByAttendeeIdAndEventId(attendeeId, eventId) != null;
    }
}

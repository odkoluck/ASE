package bookmarkingService.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bookmarkingService.model.Bookmark;


@Repository
public interface BookmarkingRepository extends MongoRepository<Bookmark, String> {
	
    List<Bookmark> findByAttendeeId(String attendeeId);
    Bookmark findByAttendeeIdAndEventId(String attendeeId, String eventId);
}

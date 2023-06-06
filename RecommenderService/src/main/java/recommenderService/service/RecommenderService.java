package recommenderService.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import recommenderService.module.AttendeePreference;
import recommenderService.repository.RecommenderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecommenderService {
    private final RecommenderRepository recommenderRepository;

    
    @Autowired
    public RecommenderService(RecommenderRepository recommenderRepository) {
        this.recommenderRepository = recommenderRepository;

    }
    public Optional<AttendeePreference> getAttendeePreference(String attendeeId) {
        return recommenderRepository.findById(attendeeId);
    }
    
    
    public List<AttendeePreference> getAllAttendeePreferences() {
        return recommenderRepository.findAll();
    }

	public void updateRecommendations(String attendeeId, String eventId) {;
	 System.out.println("update Message received: " + "Attendee:" + attendeeId + "Event:" + eventId );
	        Optional<AttendeePreference> attendeePreferenceOptional = recommenderRepository.findById(attendeeId);
	        AttendeePreference attendeePreference;
	        if (attendeePreferenceOptional.isPresent()) {
	       //	 System.out.println(" if Message received: " + "Attendee:" + attendeeId + "Event:" + eventId );
	            attendeePreference = attendeePreferenceOptional.get();
	        } else {
	        //	 System.out.println(" else Message received: " + "Attendee:" + attendeeId + "Event:" + eventId );
	            attendeePreference = new AttendeePreference();
	            attendeePreference.setAttendeeId(attendeeId);
	        }
	        attendeePreference.bookmarkedEventIds.add(eventId);
	        //System.out.println("add Message received: " + "Attendee:" + attendeeId + "Event:" + eventId );
	      recommenderRepository.save(attendeePreference); 
	}
	public String sendNotification(String eventName, String attendeeId) {
		System.out.println("Notification sent!" + eventName + attendeeId);
		return null;
	}
}

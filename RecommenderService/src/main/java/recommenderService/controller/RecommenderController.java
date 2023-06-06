package recommenderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import recommenderService.BookmarkedEventIdsRequest;
import recommenderService.module.AttendeePreference;
import recommenderService.repository.RecommenderRepository;
import recommenderService.service.RecommenderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
public class RecommenderController {

    private final RecommenderRepository recommenderRepository;

    @Autowired
    public RecommenderController(RecommenderRepository recommenderRepository) {
        this.recommenderRepository = recommenderRepository;
    }

    @GetMapping("/{attendeeId}/preference")
    public ResponseEntity<AttendeePreference> getAttendeePreference(@PathVariable String attendeeId) {
        Optional<AttendeePreference> attendeePreferenceOptional = recommenderRepository.findById(attendeeId);
        if (attendeePreferenceOptional.isPresent()) {
        	System.out.println("attendee present:" + attendeeId);
            AttendeePreference attendeePreference = attendeePreferenceOptional.get();
            return ResponseEntity.ok(attendeePreference);
        } else {
        	System.out.println("attendee not present"+ attendeeId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{attendeeId}/preference")
    public ResponseEntity<AttendeePreference> setAttendeePreference(
    		@PathVariable String attendeeId,
            @RequestBody BookmarkedEventIdsRequest request) {
        List<String> bookmarkedEventIds = request.getBookmarkedEventIds();
        Optional<AttendeePreference> attendeePreferenceOptional = recommenderRepository.findById(attendeeId);
        AttendeePreference attendeePreference;
        if (attendeePreferenceOptional.isPresent()) {
            attendeePreference = attendeePreferenceOptional.get();
        } else {
            attendeePreference = new AttendeePreference();
            attendeePreference.setAttendeeId(attendeeId);
        }
        attendeePreference.setBookmarkedEventIds(bookmarkedEventIds);
        AttendeePreference savedPreference = recommenderRepository.save(attendeePreference);
        return ResponseEntity.ok(savedPreference);
    }
}

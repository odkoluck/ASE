package bookmarkingService.controller;

import bookmarkingService.service.BookmarkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookmarkingControllerTest {
    private BookmarkingController bookmarkingController;

    @Mock
    private BookmarkingService bookmarkingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookmarkingController = new BookmarkingController(bookmarkingService);
    }

   /* @Test
    void bookmarkEvent_shouldReturnCreatedStatus() {
        int attendeeId = 1;
        int eventId = 1;

        ResponseEntity<Void> response = bookmarkingController.bookmarkEvent(attendeeId, eventId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(bookmarkingService, times(1)).bookmarkEvent(attendeeId, eventId);
    }
*/
   /* @Test
    void unbookmarkEvent_shouldReturnOkStatus() {
        int attendeeId = 1;
        int eventId = 1;

        ResponseEntity<Void> response = bookmarkingController.unbookmarkEvent(attendeeId, eventId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookmarkingService, times(1)).unbookmarkEvent(attendeeId, eventId);
    }
*/
   /* @Test
    void getBookmarkedEventIds_shouldReturnListOfEventIds() {
        int attendeeId = 1;
        List<Integer> eventIds = Arrays.asList(1, 2, 3);

        when(bookmarkingService.getBookmarkedEventIds(attendeeId)).thenReturn(eventIds);

        ResponseEntity<List<Integer>> response = bookmarkingController.getBookmarkedEventIds(attendeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventIds, response.getBody());
        verify(bookmarkingService, times(1)).getBookmarkedEventIds(attendeeId);
    }
*//*
    @Test
    void isEventBookmarked_shouldReturnTrue() {
        int attendeeId = 1;
        int eventId = 1;

        when(bookmarkingService.isEventBookmarked(attendeeId, eventId)).thenReturn(true);

        ResponseEntity<Boolean> response = bookmarkingController.isEventBookmarked(attendeeId, eventId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(bookmarkingService, times(1)).isEventBookmarked(attendeeId, eventId);
    }*/
}

/*package com.example.demo;

import com.example.demo.domain.Feedback;
import com.example.demo.application.FeedbackService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.example.demo.presentation.FeedbackController;

class FeedbackControllerTest {

    @Test
    void createFeedback_ValidInputParameters_ReturnsOkResponse() {
        // Arrange
        String eventId = "event1";
        Feedback feedback = new Feedback();
        // Set the necessary properties of the feedback object
        feedback.setLocation(4);
        feedback.setAccuracy(5);
        feedback.setPerformance(4);
        feedback.setDescription("Good event");

        FeedbackService feedbackServiceMock = mock(FeedbackService.class);
        FeedbackController feedbackController = new FeedbackController(feedbackServiceMock);

        // Stub the behavior of the feedbackService.createFeedback() method
        Feedback createdFeedback = new Feedback();
        // Set the necessary properties of the created feedback object
        createdFeedback.setEventId(eventId);
        createdFeedback.setUserId("user1");
        // You can set other properties as needed

        when(feedbackServiceMock.createFeedback(eventId, "user1", feedback)).thenReturn(createdFeedback);

        // Act
        ResponseEntity<?> responseEntity = feedbackController.createFeedback(eventId, feedback, "token123");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully created feedback!", responseEntity.getBody());

        // Verify that the feedbackService.createFeedback() method was called with the correct arguments
        verify(feedbackServiceMock, times(1)).createFeedback(eventId, "user1", feedback);
        verifyNoMoreInteractions(feedbackServiceMock);
    }
}
*/


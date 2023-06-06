package com.example.demo;
        import com.example.demo.domain.Feedback;
        import com.example.demo.domain.FeedbackRepository;
        import com.example.demo.application.FeedbackService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.*;
        import org.springframework.amqp.rabbit.core.RabbitTemplate;
        import java.util.*;
        import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

public class FeedbackServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetAllFeedbacks() {
        List<Feedback> expectedFeedbacks = new ArrayList<>();
        expectedFeedbacks.add(new Feedback("eventId1", "userId1", 5, 4, 3, "was cool"));
        expectedFeedbacks.add(new Feedback("eventId2", "userId2", 3, 3, 1, "not that cool"));


        when(feedbackRepository.findAll()).thenReturn(expectedFeedbacks);

        List<Feedback> actualFeedbacks = feedbackService.getAllFeedbacks();

        assertEquals(expectedFeedbacks, actualFeedbacks);
        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testGetFeedbackById() {
        String feedbackId = "1";
        Feedback expectedFeedback = new Feedback("eventId1", "userId1", 5, 4, 3, "was cool");

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(expectedFeedback));

        Feedback actualFeedback = feedbackService.getFeedbackById(feedbackId);

        assertEquals(expectedFeedback, actualFeedback);
        verify(feedbackRepository, times(1)).findById(feedbackId);
    }

    @Test
    void testGetFeedbackByEventId() {
        // Arrange
        FeedbackRepository mockRepository = Mockito.mock(FeedbackRepository.class);
        List<Feedback> expectedFeedbacks = new ArrayList<>();
        expectedFeedbacks.add(new Feedback("eventId1", "userId1", 5, 4, 3, "was cool"));
        expectedFeedbacks.add(new Feedback("4567", "userId1", 5, 4, 3, "was cool"));
        when(mockRepository.findByEventId("eventId1")).thenReturn(expectedFeedbacks);
        FeedbackService feedbackService = new FeedbackService(mockRepository, null);

        // Act
        List<Feedback> actualFeedbacks = feedbackService.getFeedbackByEventId("eventId1");

        // Assert
        assertEquals(expectedFeedbacks, actualFeedbacks);
        verify(mockRepository, times(1)).findByEventId("eventId1");
    }

    @Test
    void createFeedback_ValidRequestBody_ReturnsCreatedFeedback() {
        // Arrange
        String eventID = "event1";
        String userID = "user1";
        Feedback feedback = new Feedback();
        feedback.setLocation(4);
        feedback.setAccuracy(5);
        feedback.setPerformance(4);
        feedback.setDescription("Good event");

        when(feedbackRepository.insert(any(Feedback.class))).thenReturn(feedback);

        // Act
        Feedback createdFeedback = feedbackService.createFeedback(eventID, userID, feedback);

        // Assert
        assertEquals(eventID, createdFeedback.getEventId());
        assertEquals(userID, createdFeedback.getUserId());
        assertEquals(feedback.getLocation(), createdFeedback.getLocation());
        assertEquals(feedback.getAccuracy(), createdFeedback.getAccuracy());
        assertEquals(feedback.getPerformance(), createdFeedback.getPerformance());
        assertEquals(feedback.getRating(), createdFeedback.getRating());
        assertEquals(feedback.getDescription(), createdFeedback.getDescription());
        verify(feedbackRepository, times(1)).insert(any(Feedback.class));
    }



    @Test
    void testDeleteFeedback() {
        // Arrange
        FeedbackRepository mockRepository = Mockito.mock(FeedbackRepository.class);
        FeedbackService feedbackService = new FeedbackService(mockRepository, null);

        // Act
        feedbackService.deleteFeedback("feedbackId1");

        // Assert
        verify(mockRepository, times(1)).deleteById("feedbackId1");
    }

    @Test
    void inputValidation_ValidInputParameters_DoesNotThrowException() {
        // Arrange
        String eventId = "event1";
        Feedback feedback = new Feedback();
        feedback.setLocation(4);
        feedback.setAccuracy(5);
        feedback.setPerformance(4);
        feedback.setDescription("Good event");

        // Act and Assert
        try {
            FeedbackService.inputValidation(eventId, feedback);
        } catch (Exception e) {
            // Fail the test if an exception is thrown
            fail("Expected no exception, but got: " + e);
        }
    }


    @Test
    void testGetFeedbackByUserId() {
        // Arrange
        FeedbackRepository mockRepository = Mockito.mock(FeedbackRepository.class);
        List<Feedback> expectedFeedbacks = new ArrayList<>();
        expectedFeedbacks.add(new Feedback("eventId1", "userId1", 5, 4, 3, "was cool"));
        expectedFeedbacks.add(new Feedback("eventId1", "userId1", 5, 4, 3, "was cool"));
        when(mockRepository.findByUserId("userId1")).thenReturn(expectedFeedbacks);
        FeedbackService feedbackService = new FeedbackService(mockRepository, null);

        // Act
        List<Feedback> actualFeedbacks = feedbackService.getFeedbackByUserId("userId1");

        // Assert
        assertEquals(expectedFeedbacks, actualFeedbacks);
        verify(mockRepository, times(1)).findByUserId("userId1");
    }
}



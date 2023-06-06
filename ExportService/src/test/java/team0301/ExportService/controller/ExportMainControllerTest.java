package team0301.ExportService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team0301.ExportService.model.Event;
import team0301.ExportService.repository.EventRepository;
import team0301.ExportService.service.ExportService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class ExportMainControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EventRepository eventRepository;

    @MockBean
    private ExportService exportService;

    @InjectMocks
    private ExportMainController exportMainController;

    Event RECORD_1 = new Event("1", "java", "2023.01.01", "2023.01.02", "uni", "kkkk");
    Event RECORD_2 = new Event("2", "nodeJS", "2023.05.01", "2023.05.02", "uni", "jjj");


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(exportMainController).build();
    }

    //     @Test
    // public void getAllEvent() throws Exception {
    //         List<Event> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));

    //         eventRepository.insert(RECORD_1);
    //         eventRepository.insert(RECORD_2);

    //         assertEquals(2, exportService.getAllEvents().size());





    //     }
}
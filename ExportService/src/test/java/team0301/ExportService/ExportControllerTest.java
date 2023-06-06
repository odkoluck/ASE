package team0301.ExportService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team0301.ExportService.controller.ExportMainController;
import team0301.ExportService.model.Event;
import team0301.ExportService.model.RegistredAttendee;
import team0301.ExportService.repository.EventRepository;
import team0301.ExportService.repository.RegisteredAttendeeRepository;
import team0301.ExportService.scheme.ResponseScheme;
import team0301.ExportService.service.ExportService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ExportMainController.class)
public class ExportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ExportMainController mainController;

    @Mock
    private ExportService exportService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private RegisteredAttendeeRepository registeredAttendeeRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }


    // @Test
    // public void exportGetAllEventTest() throws  Exception{

    //     Event RECORD_1 = new Event("1", "java", "2023.01.01", "2023.01.02", "uni", "kkkk");
    //     Event RECORD_2 = new Event("2", "nodeJS", "2023.05.01", "2023.05.02", "uni", "jjj");
    //     Event RECORD_3 = new Event("3", "python", "2023.07.01", "2023.07.02", "uni", "ggg");

    //     RegistredAttendee RECORD_4 = new RegistredAttendee("1", "2");
    //     RegistredAttendee RECORD_5 = new RegistredAttendee("2", "3");
    //     RegistredAttendee RECORD_6 = new RegistredAttendee("2", "1");

    //     ResponseScheme RECORD_7 = new ResponseScheme("java", "2023.01.01", "2023.01.02", "uni", "kkkk");
    //     ResponseScheme RECORD_8 = new ResponseScheme("python", "2023.07.01", "2023.07.02", "uni", "ggg");


    //     List<RegistredAttendee> registredAttendeeList = new ArrayList<>(Arrays.asList(RECORD_4, RECORD_5, RECORD_6));
    //     List<Event> eventList = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
    //     List<ResponseScheme> responseList = new ArrayList<>(Arrays.asList(RECORD_7, RECORD_8));

    //     //when(exportService.getAllEvent()).thenReturn(eventList);

    //     mockMvc.perform(MockMvcRequestBuilders.get("/export/test")
    //             .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("1, java, 2023.01.01, 2023.01.02, uni, kkkk"))
    //             .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("2, nodeJS, 2023.05.01, 2023.05.02, uni, jjj"))
    //             .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("3, python, 2023.07.01, 2023.07.02, uni, ggg"));

    //     //verify(exportService, times(1)).getAllEvent();
    //     verifyNoMoreInteractions(exportService);





    //     //assertEquals(responseList.get(0).getEventName(), exportService.exportXML("2").get(0).getEventName());

    // }



}

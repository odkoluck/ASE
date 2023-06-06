package team0301.ExportService.service;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team0301.ExportService.model.Event;
import team0301.ExportService.model.EventXMLFormat;
import team0301.ExportService.model.RegistredAttendee;
import team0301.ExportService.repository.EventRepository;
import team0301.ExportService.repository.RegisteredAttendeeRepository;
import team0301.ExportService.scheme.ResponseScheme;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportService {

    @Autowired
    private RegisteredAttendeeRepository registeredAttendeeRepository;

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Map<String, Object> exportJson(String userId) {

        List<RegistredAttendee> registredAttendeeList = new ArrayList<>();
        List<Event> eventList = new ArrayList<>();

        registredAttendeeList = registeredAttendeeRepository.findAll();
        eventList = eventRepository.findAll();

        List<ResponseScheme> responseList = new ArrayList<>();

        Map<String, Object> requestResponse = new HashMap<>();

        try {
            for (RegistredAttendee registredAttendee : registredAttendeeList) {
                if (userId.equals(registredAttendee.getUserId())) {
                    System.out.println(userId);
                    System.out.println(registredAttendee.getRegistredEventId());
                    String eventId = registredAttendee.getRegistredEventId();
                    // find event by eventId
                    Event event = eventRepository.findById(eventId).get();

                    System.out.println("EventName : "+event.getEventName());
                    System.out.println("startDate : "+event.getStartDate());
                    System.out.println("endDate : "+event.getEndDate());
                    System.out.println("Description : "+event.getDescription());
                    System.out.println("Location : "+event.getLocation());
                    responseList.add(ResponseScheme.getInstance(event.getEventName(), event.getStartDate(), event.getEndDate(), event.getDescription(), event.getLocation()));

                }
            }

            requestResponse.put("status", "ok");
            requestResponse.put("message", "success");
            requestResponse.put("payload", responseList);
        } catch (Exception e) {
            requestResponse.put("status", "failed");
            requestResponse.put("message", e.getMessage());
        }
        return requestResponse;

    }

    // export XML format
    public List<EventXMLFormat> exportXML(String userId) {

        List<RegistredAttendee> registredAttendeeList = new ArrayList<>();
        //List<Event> eventList = new ArrayList<>();

        registredAttendeeList = registeredAttendeeRepository.findAll();
        //eventList = eventRepository.findAll();

        List<EventXMLFormat> responseList = new ArrayList<>();

        /*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;*/

        try {


            /*dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // add elements to Document
            Element rootElement = doc.createElement("Events");
            // append root element to document
            doc.appendChild(rootElement);*/

            for (RegistredAttendee registredAttendee : registredAttendeeList) {
                if (userId.equals(registredAttendee.getUserId())) {
                    System.out.println(userId);
                    System.out.println(registredAttendee.getRegistredEventId());

                    String eventId = registredAttendee.getRegistredEventId();
                    Event event = eventRepository.findById(eventId).get();

                    EventXMLFormat eventXML = new EventXMLFormat();

                    eventXML.setEventName(event.getEventName());
                    eventXML.setLocation(event.getLocation());
                    eventXML.setStartDate(event.getStartDate());
                    eventXML.setEndDate(event.getEndDate());
                    eventXML.setDescription(event.getDescription());
                    responseList.add(eventXML);

                }
            }
            /*// for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("create_events.xml"));

            // write data
            transformer.transform(source, console);
            transformer.transform(source, file);*/



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return responseList;

    }

    // Export ICalendar Format

    public ResponseEntity generateCalenderFile(String userId) {

        List<RegistredAttendee> registredAttendeeList = new ArrayList<>();
        List<Event> eventList = new ArrayList<>();

        registredAttendeeList = registeredAttendeeRepository.findAll();
        eventList = eventRepository.findAll();

        try {

            /* Create calendar */
            Calendar calendar = new Calendar();
            calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
            calendar.getProperties().add(Version.VERSION_2_0);
            calendar.getProperties().add(CalScale.GREGORIAN);

            for (RegistredAttendee registredAttendee : registredAttendeeList) {
                if (userId.equals(registredAttendee.getUserId())) {
                    String eventId = registredAttendee.getRegistredEventId();
                    Event event = eventRepository.findById(eventId).get();

                    System.out.println(userId);
                    /* Generate unique identifier */
                    UidGenerator ug = new RandomUidGenerator();
                    Uid uid = ug.generateUid();

                    /* Create the event */
                    String eventSummary = event.getEventName();

                    DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

                    LocalDate startDate = LocalDate.parse(event.getStartDate(), sourceFormatter);
                    LocalDate endDate = LocalDate.parse(event.getEndDate(), sourceFormatter);
                    LocalTime time = LocalTime.MIDNIGHT;
                    LocalDateTime startDateTime = LocalDateTime.of(startDate, time);
                    LocalDateTime endDateTime = LocalDateTime.of(endDate, time);

                    String isoStart = startDateTime.format(targetFormatter);
                    String isoEnd= endDateTime.format(targetFormatter);

                    DateTime start = new DateTime(isoStart);
                    DateTime end = new DateTime(isoEnd);


                    VEvent calEvent = new VEvent(start, end, eventSummary);

                    calEvent.getProperties().add(uid);

                    /* Add event to calendar */
                    calendar.getComponents().add(calEvent);



                }

            }

            /* Create a file */
            byte[] calendarByte = calendar.toString().getBytes();
            Resource resource = new ByteArrayResource(calendarByte);

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mycalendar.ics");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            return ResponseEntity.ok().headers(header).contentType(MediaType.
                            APPLICATION_OCTET_STREAM)
                    .body(resource);

        }catch (Exception e) {
            System.out.println("you have not registred any events");
        }
        return null;

    }




}

package team0301.ExportService.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team0301.ExportService.model.Event;
import team0301.ExportService.model.EventXMLFormat;
import team0301.ExportService.repository.EventRepository;
import team0301.ExportService.service.ExportService;


import java.util.*;


@RestController
@RequestMapping(path="/export")
public class ExportMainController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private EventRepository eventRepository;



    @GetMapping
    public List<Event> getAllEvent(){
        return exportService.getAllEvents();
    }

    @GetMapping("/json/{userId}")
    public Map<String, Object> exportJson(@PathVariable String userId) {
        return exportService.exportJson(userId);
    }

    @GetMapping(value = "/xml", produces = "application/xml")
    public List<EventXMLFormat> exportXML(@RequestParam String userId) {
        return exportService.exportXML(userId);
    }

    @GetMapping("/standard")
    public ResponseEntity generateCalenderFile(@RequestParam String userId) {
        return exportService.generateCalenderFile(userId);
    }
        



}

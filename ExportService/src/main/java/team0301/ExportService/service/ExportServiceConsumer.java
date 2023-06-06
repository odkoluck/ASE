package team0301.ExportService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team0301.ExportService.model.CalenderExportService;
import team0301.ExportService.model.RegistredAttendee;
import team0301.ExportService.model.Event;
import team0301.ExportService.repository.CalendarExportRepository;
import team0301.ExportService.repository.EventRepository;
import team0301.ExportService.repository.RegisteredAttendeeRepository;

@Component
public class  ExportServiceConsumer {
	
	@Autowired
	private CalendarExportRepository calendarExportRepository;
	
	@Autowired
	private RegisteredAttendeeRepository registeredAttendeeRepository;
	
	@Autowired
	private EventRepository eventRepository;

	
	@RabbitListener(queues ="eventQueue" )
	public void consumerExportServiceQueue(Event event) {
		
		System.out.println(event.getEventId());
		System.out.println(event.getEventName());
		System.out.println(event.getStartDate());
		System.out.println(event.getEndDate());
		System.out.println(event.getDescription());
		System.out.println(event.getLocation());
		
		
		try {
			Event newEvent = new Event();
			newEvent.setEventName(event.getEventName());
			newEvent.setEventId(event.getEventId());
			newEvent.setStartDate(event.getStartDate());
			newEvent.setEndDate(event.getEndDate());
			newEvent.setDescription(event.getDescription());
			newEvent.setLocation(event.getLocation());
			eventRepository.insert(newEvent);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	
	@RabbitListener(queues ="test_Register_Queue" )
	public void registredAttendeeQueue(RegistredAttendee registredAttendee) {
		
		//System.out.println(registredAttendee.getId());
		System.out.println(registredAttendee.getUserId());
		System.out.println(registredAttendee.getRegistredEventId());
		
		
		try {
			RegistredAttendee registeredEvent = new RegistredAttendee();
			registeredEvent.setUserId(registredAttendee.getUserId());;
			registeredEvent.setRegistredEventId(registredAttendee.getRegistredEventId());
			registeredAttendeeRepository.insert(registeredEvent);
			
		}catch(Exception e) {
			System.out.println("nono");
		}
		
		
		
	}

}

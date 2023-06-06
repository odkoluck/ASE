package team0301.ExportService.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="calenderExporter")
public class CalenderExporter {
	

	CalenderExportService event;
	String format;
	
	public CalenderExporter(CalenderExportService event, String format) {
		this.event = event;
		this.format = format;
	}

	public CalenderExportService getEvent() {
		return event;
	}

	public void setEvent(CalenderExportService event) {
		this.event = event;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	
	
	
	
	

}

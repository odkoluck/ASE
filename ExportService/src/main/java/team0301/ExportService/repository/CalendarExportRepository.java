package team0301.ExportService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team0301.ExportService.model.CalenderExportService;

@Repository
public interface CalendarExportRepository extends MongoRepository <CalenderExportService, String> {

}

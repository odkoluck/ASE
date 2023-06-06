package team0301.ExportService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team0301.ExportService.model.RegistredAttendee;

@Repository
public interface RegisteredAttendeeRepository extends MongoRepository <RegistredAttendee, String> {

}

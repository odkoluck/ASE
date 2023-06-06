package com.team0301.AttendanceService.repository;

import com.team0301.AttendanceService.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
}

package recommenderService.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import recommenderService.module.AttendeePreference;

@Repository
public interface RecommenderRepository extends MongoRepository<AttendeePreference, String> {

}

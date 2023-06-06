package com.example.Event;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends MongoRepository<Event, String> {

    List<Event>findByEmailAddress(String emailAddress);
}

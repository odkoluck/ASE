package com.example.demo.SearchService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SearchRepository extends MongoRepository<SearchCriteria, String> {

    Optional<SearchCriteria> findById(String eventId);
}

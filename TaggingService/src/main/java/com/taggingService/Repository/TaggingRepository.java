package com.taggingService.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.taggingService.Model.Tag;

@Repository
public interface TaggingRepository extends MongoRepository<Tag, String> {
    boolean existsByName(String name);
    Tag findByName(String name);
}

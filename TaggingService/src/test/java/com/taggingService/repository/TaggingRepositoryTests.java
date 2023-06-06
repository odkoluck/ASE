package com.taggingService.repository;

import com.taggingService.Model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class TaggingRepositoryTests {
/*
    @Autowired
    private TaggingRepository taggingRepository;

    @BeforeEach
    public void setUp() {
        // Perform any setup needed before each test
    }

    @AfterEach
    public void tearDown() {
        // Perform any cleanup needed after each test
        taggingRepository.deleteAll();
    }

    @Test
    public void testSaveTag() {
        // Create a new tag
        Tag tag = new Tag("Music", null, null);

        // Save the tag to the repository
        Tag savedTag = taggingRepository.save(tag);

        // Retrieve the tag by its ID
        Tag retrievedTag = taggingRepository.findById(savedTag.getId()).orElse(null);

        // Assert that the retrieved tag is not null
        assertNotNull(retrievedTag);

        // Assert that the retrieved tag has the same name as the saved tag
        assertEquals(tag.getName(), retrievedTag.getName());
    }

    @Test
    public void testFindAllTags() {
        // Create some tags
        Tag tag1 = new Tag("Music", null, null);
        Tag tag2 = new Tag("Sports", null, null);

        // Save the tags to the repository
        taggingRepository.save(tag1);
        taggingRepository.save(tag2);

        // Retrieve all tags from the repository
        List<Tag> tags = taggingRepository.findAll();

        // Assert that the number of retrieved tags matches the number of saved tags
        assertEquals(2, tags.size());
    }
*/
    // Add more test methods as needed
}

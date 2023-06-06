package com.example.demo.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class SearchService {

    private final SearchRepository searchRepository;


    @Autowired
    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }


    public SearchCriteria updateEvent(SearchCriteria searchCriteria) {
        Optional<SearchCriteria> optionalResult = searchRepository.findById(searchCriteria.getId());
        if (optionalResult.isPresent()) {
            SearchCriteria result = optionalResult.get();
            result.setEmailAddress(searchCriteria.getEmailAddress());
            result.setEndDate(searchCriteria.getEndDate());
            result.setLocation(searchCriteria.getLocation());
            result.setDescription(searchCriteria.getDescription());
            result.setStartTime(searchCriteria.getStartTime());
            result.setCapacity(searchCriteria.getCapacity());
            result.setStartDate(searchCriteria.getStartDate());
            result.setEventName(searchCriteria.getEventName());
            result.setType(searchCriteria.getType());
            return searchRepository.save(result);
        } else {
            throw new NoSuchElementException("SearchCriteria with ID " + searchCriteria.getId() + " not found.");
        }
    }


    public void insertEvent(SearchCriteria searchCriteria) {
        searchRepository.insert(searchCriteria);
    }

    public void deleteEvent(String id) {
        searchRepository.deleteById(id);
    }

    public List<SearchCriteria> searchEvents(SearchCriteria searchCriteria) {
        List<SearchCriteria> results = new ArrayList<>();

        // Retrieve all search results from the database (or any other data source)
        List<SearchCriteria> searchResults = searchRepository.findAll();

        // Filter the search results based on the specified search criteria
        for (SearchCriteria result : searchResults) {
            if (searchCriteria.getId() != null && !result.getId().equals(searchCriteria.getId())) {
                continue;
            }
            if (searchCriteria.getStartDate() != null && !result.getStartDate().equals(searchCriteria.getStartDate())) {
                continue;
            }
            if (searchCriteria.getEndDate() != null && !result.getEndDate().equals(searchCriteria.getEndDate())) {
                continue;
            }
            if (searchCriteria.getEventName() != null && !result.getEventName().equals(searchCriteria.getEventName())) {
                continue;
            }
            if (searchCriteria.getLocation() != null && !result.getLocation().equals(searchCriteria.getLocation())) {
                continue;
            }
            if (searchCriteria.getStartTime() != null && !result.getStartTime().equals(searchCriteria.getStartTime())) {
                continue;
            }
            if (searchCriteria.getCapacity() != null && !result.getCapacity().equals(searchCriteria.getCapacity())) {
                continue;
            }
            if (searchCriteria.getType() != null && !result.getType().equals(searchCriteria.getType())) {
                continue;
            }
            if (searchCriteria.getDescription() != null && !result.getDescription().equals(searchCriteria.getDescription())) {
                continue;
            }
            if (searchCriteria.getEmailAddress() != null && !result.getEmailAddress().equals(searchCriteria.getEmailAddress())) {
                continue;
            }
            results.add(result);
        }
        return results;
    }

}



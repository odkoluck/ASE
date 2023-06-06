package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReportEvent;

@Repository
public interface ReportEventRepository extends MongoRepository <ReportEvent, String> {

}

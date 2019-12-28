package com.training.SpringBootTask.repository;

import com.training.SpringBootTask.entity.UserParameterHistoryObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserParameterRepository extends MongoRepository<UserParameterHistoryObject, String> {
    @Query("{'userId' : {$eq : ?0}, 'date' : {$gte : ?1, $lte : ?2}}")
    List<UserParameterHistoryObject> getUserParametersForPeriod(String id, LocalDate from, LocalDate to);
}

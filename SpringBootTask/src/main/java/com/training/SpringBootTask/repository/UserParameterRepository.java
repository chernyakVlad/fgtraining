package com.training.SpringBootTask.repository;

import com.training.SpringBootTask.entity.UserParameterHistoryObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserParameterRepository extends MongoRepository<UserParameterHistoryObject, String> {
}

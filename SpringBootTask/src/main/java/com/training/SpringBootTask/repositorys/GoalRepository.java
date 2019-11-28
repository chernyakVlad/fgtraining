package com.training.SpringBootTask.repositorys;

import com.training.SpringBootTask.models.SMARTGoal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GoalRepository extends MongoRepository<SMARTGoal, String> {
    List<SMARTGoal> findByUserId(String userId);
}

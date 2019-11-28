package com.training.SpringBootTask.repositorys;

import com.training.SpringBootTask.models.GoalTimeBound;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GoalTimeBoundRepository extends MongoRepository<GoalTimeBound, String> {
}

package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.GoalTimeBound;
import com.training.SpringBootTask.models.Goal;

import java.util.List;

public interface GoalService {
    Goal getById(String id);
    List<Goal> getAll();
    List<Goal> getByUserId(String userId);
    Goal save(Goal goal);
    Goal update(String id, Goal goal);
    List<GoalTimeBound> getTimeBounForGoal(String goalId);
    void delete(String id);
}

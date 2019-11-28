package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.GoalTimeBound;
import com.training.SpringBootTask.models.SMARTGoal;

import java.util.List;

public interface GoalService {
    SMARTGoal getById(String id);
    List<SMARTGoal> getAll();
    List<SMARTGoal> getByUserId(String userId);
    SMARTGoal save(SMARTGoal goal);
    SMARTGoal update(String id, SMARTGoal goal);
    List<GoalTimeBound> getTimeBounForGoal(String goalId);
}

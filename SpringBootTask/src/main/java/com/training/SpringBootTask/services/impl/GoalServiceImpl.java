package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.entity.Goal;
import com.training.SpringBootTask.exception.ItemNotFoundException;
import com.training.SpringBootTask.repository.GoalRepository;
import com.training.SpringBootTask.services.GoalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal getById(String id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Goal with id - " + id + "not found"));
    }

    @Override
    public List<Goal> getAll() {
        List<Goal> goals = goalRepository.findAll();
        if(goals.size() <= 0) {
            throw new ItemNotFoundException("No goals found");
        }
        return goals;
    }

    @Override
    public List<Goal> getByUserId(String userId) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        if(goals.size() <= 0) {
            throw new ItemNotFoundException("User with id - " + userId + " has no goals");
        }
        return goals;
    }

    @Override
    public Goal save(Goal goal) {
        return goalRepository.insert(goal);
    }

    @Override
    public Goal update(String id, Goal uGoal) {
        Goal goal = this.getById(id);
        BeanUtils.copyProperties(uGoal, goal, "id");
        return goalRepository.save(goal);
    }

    @Override
    public void delete(String id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("No goal found with id - " + id));
        goalRepository.delete(goal);
    }
}

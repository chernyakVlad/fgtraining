package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.entity.Exercise;
import com.training.SpringBootTask.services.ExerciseGenerationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExerciseGenerationServiceImpl implements ExerciseGenerationService {
    private static List<Exercise> exercises = Arrays.asList(
            new Exercise(2500, 1.5, 8, "two hours running"),
            new Exercise(2501, 1.6, 8, "two hours running"),
            new Exercise(2502, 1.7, 8, "two hours running"),
            new Exercise(2503, 1.8, 8, "two hours running")
    );

    @Override
    public Exercise getExerciseForToday(String userId) {
        Exercise exercise = new Exercise(2500, 1.5, 8, "two hours running", new Date());
        return exercise;
    }

    @Override
    public List<Exercise> getExerciseForMonth(String userId) {
        return exercises;
    }
}

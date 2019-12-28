package com.training.SpringBootTask.services;

import com.training.SpringBootTask.entity.Exercise;

import java.util.List;

public interface ExerciseGenerationService {
    Exercise getExerciseForToday(String userId);
    List<Exercise> getExerciseForMonth(String userId);
}

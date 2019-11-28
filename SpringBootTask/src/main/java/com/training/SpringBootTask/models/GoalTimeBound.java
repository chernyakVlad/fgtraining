package com.training.SpringBootTask.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goal_time_bound")
public class GoalTimeBound {

    private Integer timeInDay;

    public Integer getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(Integer timeInDay) {
        this.timeInDay = timeInDay;
    }
}

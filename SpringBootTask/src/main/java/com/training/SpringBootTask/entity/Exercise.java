package com.training.SpringBootTask.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "exercise")
public class Exercise {

    private double calories;
    private double water;
    private double sleep;
    private String activity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    public Exercise(double calories, double water, double sleep, String activity) {
        this.calories = calories;
        this.water = water;
        this.sleep = sleep;
        this.activity = activity;
    }

    public Exercise(double calories, double water, double sleep, String activity, Date date) {
        this.calories = calories;
        this.water = water;
        this.sleep = sleep;
        this.activity = activity;
        this.date = date;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getSleep() {
        return sleep;
    }

    public void setSleep(double sleep) {
        this.sleep = sleep;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

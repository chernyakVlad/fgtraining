package com.training.SpringBootTask.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "goal")
public class Goal {
    @Transient
    public static final String SEQUENCE_NAME = "goals_sequence";

    @Id
    private String id;

    private String userId;

    private String description;

    private String measuring;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int measureFrom;

    private int measureTo;

    private String measureLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasuring() {
        return measuring;
    }

    public void setMeasuring(String measuring) {
        this.measuring = measuring;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getMeasureFrom() {
        return measureFrom;
    }

    public void setMeasureFrom(int measureFrom) {
        this.measureFrom = measureFrom;
    }

    public int getMeasureTo() {
        return measureTo;
    }

    public void setMeasureTo(int measureTo) {
        this.measureTo = measureTo;
    }

    public String getMeasureLabel() {
        return measureLabel;
    }

    public void setMeasureLabel(String measureLabel) {
        this.measureLabel = measureLabel;
    }

}

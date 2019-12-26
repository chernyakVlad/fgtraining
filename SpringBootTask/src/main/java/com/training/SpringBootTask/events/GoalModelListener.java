package com.training.SpringBootTask.events;

import com.training.SpringBootTask.entity.Goal;
import com.training.SpringBootTask.services.util.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class GoalModelListener extends AbstractMongoEventListener<Goal> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public GoalModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Goal> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(Long.toString(sequenceGenerator.generateSequence(Goal.SEQUENCE_NAME)));
        }
    }
}
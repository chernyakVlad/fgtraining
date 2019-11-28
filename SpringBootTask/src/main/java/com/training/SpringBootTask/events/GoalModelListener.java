package com.training.SpringBootTask.events;

import com.training.SpringBootTask.models.SMARTGoal;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.services.util.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class GoalModelListener extends AbstractMongoEventListener<SMARTGoal> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public GoalModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<SMARTGoal> event) {
        if ( event.getSource().getId() == null ) {
            event.getSource().setId(Long.toString(sequenceGenerator.generateSequence(SMARTGoal.SEQUENCE_NAME)));
        }
    }
}
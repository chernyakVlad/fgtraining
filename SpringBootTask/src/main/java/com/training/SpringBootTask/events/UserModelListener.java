package com.training.SpringBootTask.events;

import com.training.SpringBootTask.entity.User;
import com.training.SpringBootTask.services.util.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public UserModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        if ( event.getSource().getId() == null ) {
            event.getSource().setId(Long.toString(sequenceGenerator.generateSequence(User.SEQUENCE_NAME)));
        }
    }
}
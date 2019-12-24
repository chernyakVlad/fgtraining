package com.training.SpringBootTask.events;

import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.services.util.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenModelListener extends AbstractMongoEventListener<JwtToken> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public JwtTokenModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<JwtToken> event) {
        if ( event.getSource().getId() == null ) {
            event.getSource().setId(Long.toString(sequenceGenerator.generateSequence(JwtToken.SEQUENCE_NAME)));
        }
    }
}
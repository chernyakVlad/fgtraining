package com.training.components;

import atg.droplet.GenericFormHandler;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public class PersonFormHandler extends GenericFormHandler {
    Person person;
    NameChangeEventListener nameChangeEventListener;

    public boolean handlePersonTitle(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws IOException, ServletException {
        String newName = req.getParameter("button");
        nameChangeEventListener.nameChanged(new NameChangeEvent(this, newName));
        person.setName(newName);
        System.out.println(newName);
        return true;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public NameChangeEventListener getNameChangeEventListener() {
        return nameChangeEventListener;
    }

    public void setNameChangeEventListener(NameChangeEventListener nameChangeEventListener) {
        this.nameChangeEventListener = nameChangeEventListener;
    }
}

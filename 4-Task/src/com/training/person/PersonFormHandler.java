package com.training.person;

import atg.droplet.GenericFormHandler;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public class PersonFormHandler extends GenericFormHandler {
    private String personId;


    public boolean handlePersonTitle(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws IOException, ServletException {
        System.out.println(personId);
        req.setAttribute("personIdTest", personId);
        req.getRequestDispatcher("index.jsp").forward(req, res);
        return true;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

}

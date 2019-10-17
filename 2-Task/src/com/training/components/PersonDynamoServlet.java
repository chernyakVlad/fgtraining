package com.training.components;

import atg.beans.DynamicBeans;
import atg.droplet.Switch;
import atg.droplet.sql.SQLQueryRange;
import atg.nucleus.GenericService;
import atg.nucleus.Nucleus;
import atg.nucleus.ServiceException;
import atg.repository.servlet.ItemLookupDroplet;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.DynamoServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class PersonDynamoServlet extends DynamoServlet {

    @Override
    public void service(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        name = name.toUpperCase();
        req.setParameter("upperCaseName", name);
        req.serviceParameter("output", req, res);
    }
}
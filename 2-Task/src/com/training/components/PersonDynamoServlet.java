package com.training.components;

import atg.nucleus.GenericService;
import atg.nucleus.ServiceException;
import atg.servlet.DynamoServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class PersonDynamoServlet extends DynamoServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletOutputStream out = res.getOutputStream();
        out.println("<h1>Hello world!</h1>");
    }
}
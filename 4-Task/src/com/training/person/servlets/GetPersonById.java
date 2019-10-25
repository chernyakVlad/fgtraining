package com.training.person.servlets;

import atg.repository.Repository;
import atg.repository.RepositoryException;
import atg.repository.RepositoryItem;
import atg.repository.RepositoryView;
import atg.repository.rql.RqlStatement;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.DynamoServlet;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetPersonById extends DynamoServlet {
    public static final String QUERY_RQL = "all";
    public static final String ITEM_DESCRIPTOR = "person";
    public static final String ID_PARAM = "id";

    Repository repository;

    @Override
    public void service(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        try {
            String id = req.getParameter(ID_PARAM);
            if(id  != null) {
                RepositoryItem item = repository.getItem(id, ITEM_DESCRIPTOR);
                req.setParameter("element", item);
                req.serviceLocalParameter("output", req, res);
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}

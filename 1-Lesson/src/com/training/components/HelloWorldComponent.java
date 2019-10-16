package com.training.components;

import atg.nucleus.GenericService;
import atg.nucleus.ServiceException;

public class HelloWorldComponent extends GenericService {

    public String firstStr = "Dummy Value";

    public String getFirstStr() {
        return firstStr;
    }

    public void setFirstStr(String firstStr) {
        this.firstStr = firstStr;
    }

    @Override
    public void doStartService() throws ServiceException {
        super.doStartService();
        System.out.println("Hello ATG Component!");
        System.out.println("Test");
    }

    @Override
    public void doStopService() throws ServiceException {
        super.doStopService();
        System.out.println("Hello ATG Component! Stops now!");
    }
}
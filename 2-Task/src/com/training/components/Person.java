package com.training.components;

import atg.nucleus.GenericService;
import atg.nucleus.ServiceException;

public class Person {
    String name;

    public  Person() {
        System.out.println("Person has been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

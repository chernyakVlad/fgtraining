package com.training.components;

import atg.droplet.GenericFormHandler;

public class PersonFormHandler extends GenericFormHandler {
     String firstName;
     int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }
}

package com.training.components;

public class NameChangeWatcher implements  NameChangeEventListener {
    Person person;

    public NameChangeWatcher() {
    }

    @Override
    public void nameChanged(NameChangeEvent ev) {
        System.out.println("Name has been changed on to " + ev.name);
    }
}

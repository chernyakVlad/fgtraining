package com.training.components;

public class NameChangeEvent extends java.util.EventObject {
    String name;

    public NameChangeEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {return  name;}
}

package com.training.components;

public class NameChangeEvent extends java.util.EventObject {
    String name;
    String sessionId;

    public NameChangeEvent(Object source, String name, String sessionId) {
        super(source);
        this.name = name;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getName() {return  name;}
}

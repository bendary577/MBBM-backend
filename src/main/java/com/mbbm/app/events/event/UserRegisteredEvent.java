package com.mbbm.app.events.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class UserRegisteredEvent extends ApplicationEvent {

    private String email;

    public UserRegisteredEvent(Object source) {
        super(source);
    }

    public UserRegisteredEvent(Object source, String email) {
        super(source);
        this.email = email;
    }

}

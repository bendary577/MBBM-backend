package com.mbbm.app.events.publisher;

import com.mbbm.app.events.event.UserRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserRegisteredEvent(final String email) {
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(this, email);
        applicationEventPublisher.publishEvent(userRegisteredEvent);
    }

}

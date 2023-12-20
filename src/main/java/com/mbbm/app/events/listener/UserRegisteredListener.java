package com.mbbm.app.events.listener;

import com.mbbm.app.events.event.UserRegisteredEvent;
import com.mbbm.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author mohamed.bendary
 * this class is a listener for user registration event, used to execute list of actions required after user signup
 * actions are :
 * 1- sending welcome, activation email for the new user to activate his account
 * */
@Component
public class UserRegisteredListener implements ApplicationListener<UserRegisteredEvent> {

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        this.emailService.sendNewUserRegistrationEmail();
    }
}

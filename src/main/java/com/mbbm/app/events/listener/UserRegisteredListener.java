package com.mbbm.app.events.listener;

import com.mbbm.app.events.event.UserRegisteredEvent;
import com.mbbm.app.service.EmailService;
import org.springframework.context.ApplicationListener;

public class UserRegisteredListener implements ApplicationListener<UserRegisteredEvent> {

    private final EmailService emailService;

    public UserRegisteredListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        this.emailService.sendRegistrationEmail();
    }
}

package com.mbbm.app.exception.subscription;

public class ProfileHasNoSubscriptionException extends RuntimeException{
    public ProfileHasNoSubscriptionException(String message) {
        super(message);
    }
}

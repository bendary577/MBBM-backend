package com.mbbm.app.util.validation;

public class ValidationResult {

    String message;
    boolean isFailedRequest;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFailedRequest() {
        return isFailedRequest;
    }

    public void setFailedRequest(boolean failedRequest) {
        isFailedRequest = failedRequest;
    }
}

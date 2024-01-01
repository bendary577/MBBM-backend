package com.mbbm.app.util.validation;

import org.elasticsearch.common.recycler.Recycler;

/**
 * @author mohamed.bendary
 * Main class used to validate password against required business cases
 */
public class PasswordValidator {
    static boolean isPasswordLengthValid(String password){
        return password.length() < 10;
    }

    public static ValidationResult validatePassword(String password){
        ValidationResult validationResult = new ValidationResult();
        if(!isPasswordLengthValid(password)){
            validationResult.setMessage(ValidationMessages.PASSWORD_SHORT);
            validationResult.setFailedRequest(true);
        }
        validationResult.setMessage(ValidationMessages.VALID_PASSWORD);
        validationResult.setFailedRequest(false);
        return validationResult;
    }

}

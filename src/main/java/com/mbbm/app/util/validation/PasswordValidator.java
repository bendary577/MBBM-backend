package com.mbbm.app.util.validation;

/**
 * @author mohamed.bendary
 * Main class used to validate password against required business cases
 */
public class PasswordValidator {
    static boolean isPasswordLengthValid(String password){
        return password.length() < 10;
    }

    public static String validatePassword(String password){
        if(!isPasswordLengthValid(password)){
            return ValidationMessages.PASSWORD_SHORT;
        }
        return ValidationMessages.VALID_PASSWORD;
    }

}

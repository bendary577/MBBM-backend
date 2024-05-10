package com.mbbm.app.util.validation;

import com.mbbm.app.http.request.authentication.UserRegistrationRequestDTO;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.service.authentication.UserRegistrationService;

/**
 * @author mohamed.bendary
 * Main class used to validate password against required business cases
 */
public class NewUserValidator{

    private UserRegistrationService userRegistrationService;

    public NewUserValidator (UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    public ValidationResult validate(UserRegistrationRequestDTO newUserRequestDTO) {

        ValidationResult validationResult = new ValidationResult();

        if(newUserRequestDTO.getUsername().equals("")){
            validationResult.setMessage(ResponseMessages.NO_USERNAME_PROVIDED);
            validationResult.isFailedRequest = true;
        }

        if(newUserRequestDTO.getEmail().equals("")){
            validationResult.setMessage(ResponseMessages.NO_EMAIL_PROVIDED);
            validationResult.isFailedRequest = true;
        }

        if(userRegistrationService.checkUsernameAlreadyExists(newUserRequestDTO.getUsername())) {
            validationResult.setMessage(ResponseMessages.USERNAME_EXISTS);
            validationResult.isFailedRequest = true;
        }

        if(userRegistrationService.checkEmailAlreadyExists(newUserRequestDTO.getEmail())){
            validationResult.setMessage(ResponseMessages.EMAIL_EXISTS);
            validationResult.isFailedRequest = true;

        }
        return validationResult;
    }

}

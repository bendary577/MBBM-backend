package com.mbbm.app.util.validation;

import com.mbbm.app.http.request.NewUserRequestDTO;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mohamed.bendary
 * Main class used to validate password against required business cases
 */
public class NewUserValidator{

    private SignupService signupService;

    public NewUserValidator (SignupService signupService) {
        this.signupService = signupService;
    }

    public ValidationResult validate(NewUserRequestDTO newUserRequestDTO) {

        ValidationResult validationResult = new ValidationResult();

        if(newUserRequestDTO.getUsername().equals("")){
            validationResult.setMessage(ResponseMessages.NO_USERNAME_PROVIDED);
            validationResult.isFailedRequest = true;
        }

        if(newUserRequestDTO.getEmail().equals("")){
            validationResult.setMessage(ResponseMessages.NO_EMAIL_PROVIDED);
            validationResult.isFailedRequest = true;
        }

        if(signupService.checkUsernameAlreadyExists(newUserRequestDTO.getUsername())) {
            validationResult.setMessage(ResponseMessages.USERNAME_EXISTS);
            validationResult.isFailedRequest = true;
        }

        if(signupService.checkEmailAlreadyExists(newUserRequestDTO.getEmail())){
            validationResult.setMessage(ResponseMessages.EMAIL_EXISTS);
            validationResult.isFailedRequest = true;

        }
        return validationResult;
    }

}

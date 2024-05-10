package com.mbbm.app.controller.authentication;

import com.google.common.base.Preconditions;
import com.mbbm.app.events.publisher.UserRegisteredEventPublisher;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.User;
import com.mbbm.app.security.userDetails.UserDetailsImpl;
import com.mbbm.app.http.request.authentication.LoginRequestDTO;
import com.mbbm.app.http.request.authentication.UserRegistrationRequestDTO;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.service.authentication.LoginService;
import com.mbbm.app.service.authentication.UserRegistrationService;
import com.mbbm.app.util.validation.NewUserValidator;
import com.mbbm.app.util.validation.PasswordValidator;
import com.mbbm.app.util.validation.ValidationResult;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final LoginService loginService;

    private final UserRegistrationService userRegistrationService;

    private UserRegisteredEventPublisher userRegisteredEventPublisher;

    public AuthenticationController(
            UserRegistrationService userRegistrationService,
            LoginService loginService,
            UserRegisteredEventPublisher userRegisteredEventPublisher){
        this.userRegistrationService = userRegistrationService;
        this.loginService = loginService;
        this.userRegisteredEventPublisher = userRegisteredEventPublisher;
    }


    /***
     *
     * @param authenticationRequest
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO authenticationRequest) {
        Preconditions.checkNotNull(authenticationRequest);   //TODO :: check this line of code
        logger.info("AuthenticationController:login:Starting login request for user = " + authenticationRequest.getUsername());
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Authentication authentication = loginService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetailsObject = (UserDetailsImpl) authentication.getPrincipal();
            UserDetails userDetails = loginService.buildUserDetailsObjectUsingUsername(authenticationRequest.getUsername());

            JSONObject loginResponse = loginService.buildLoginResponse(userDetailsObject, userDetails);

            logger.info("AuthenticationController:login:successful login request for user = " + authenticationRequest.getUsername());
            return new ResponseEntity<>(loginResponse.toJSONString(), HttpStatus.OK);
        }catch(BadCredentialsException badCredentialsException){
            logger.error("AuthenticationController:login:invalid credentials for user = " + authenticationRequest.getUsername() + " " + badCredentialsException.getMessage());
            responseMessage.setMessage(badCredentialsException.getCause().getMessage());
            responseMessage.setData("");
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        } catch(Exception exception){
            logger.error("AuthenticationController:login:failed login request for user = " + authenticationRequest.getUsername() + " " + exception.getMessage());
            responseMessage.setMessage(exception.getCause().getMessage());
            responseMessage.setData("");
            return new ResponseEntity<>(ResponseMessages.UNKNOWN_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     *
     * @param newUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public ResponseEntity<?> signup(@RequestBody UserRegistrationRequestDTO newUserRequestDTO){
        logger.info("Starting signup request for user = " + newUserRequestDTO.getUsername());

        ResponseMessage responseMessage = new ResponseMessage();
        boolean isFailedRequest = false;
        try {

            //validate basic user information
            NewUserValidator newUserValidator = new NewUserValidator(this.userRegistrationService);
            ValidationResult validationResult = newUserValidator.validate(newUserRequestDTO);
            responseMessage.setMessage(validationResult.getMessage());
            isFailedRequest = validationResult.isFailedRequest();

            if(isFailedRequest){
                return new ResponseEntity<>(responseMessage, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //validate user password
            validationResult = PasswordValidator.validatePassword(newUserRequestDTO.getPassword());
            if(validationResult.isFailedRequest()){
                return new ResponseEntity<>(validationResult.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //create user object and save in db
            User newUser = userRegistrationService.createNewUser(newUserRequestDTO);
            JSONObject signupResponse = userRegistrationService.buildSignupResponse(newUser);

            logger.info("successful signup request for user = " + newUserRequestDTO.getUsername());

            //publish registration event to send email
            this.userRegisteredEventPublisher.publishUserRegisteredEvent(newUser.getEmail());
            return new ResponseEntity<>(signupResponse.toJSONString(), HttpStatus.OK);

        }catch(Exception exception){
            exception.printStackTrace();
            logger.error("failed signup request for user = " + newUserRequestDTO.getUsername() + " " + exception.getMessage());
            return new ResponseEntity<>(ResponseMessages.UNKNOWN_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
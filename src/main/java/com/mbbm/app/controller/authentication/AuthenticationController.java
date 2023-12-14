package com.mbbm.app.controller.authentication;

import com.google.common.base.Preconditions;
import com.mbbm.app.events.event.UserRegisteredEvent;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.User;
import com.mbbm.app.security.userDetails.UserDetailsImpl;
import com.mbbm.app.http.request.LoginRequestDTO;
import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.service.LoginService;
import com.mbbm.app.service.SignupService;
import com.mbbm.app.util.validation.PasswordValidator;
import com.mbbm.app.util.validation.ValidationMessages;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final SignupService signupService;

    private ApplicationEventPublisher applicationEventPublisher;

    public AuthenticationController(
            SignupService signupService,
            LoginService loginService,
            ApplicationEventPublisher applicationEventPublisher){
        this.signupService = signupService;
        this.loginService = loginService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    /***
     *
     * @param authenticationRequest
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO authenticationRequest) {
        Preconditions.checkNotNull(authenticationRequest);   //TODO :: check this line of code
        logger.info("Starting login request for user = " + authenticationRequest.getUsername());
        try {
            Authentication authentication = loginService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetailsObject = (UserDetailsImpl) authentication.getPrincipal();
            UserDetails userDetails = loginService.buildUserDetailsObjectUsingUsername(authenticationRequest.getUsername());

            JSONObject loginResponse = loginService.buildLoginResponse(userDetailsObject, userDetails);

            logger.info("successful login request for user = " + authenticationRequest.getUsername());
            return new ResponseEntity<>(loginResponse.toJSONString(), HttpStatus.OK);
        }catch(Exception exception){
            logger.error("failed login request for user = " + authenticationRequest.getUsername() + " " + exception.getMessage());
            return new ResponseEntity<>(ResponseMessages.UNKNOWN_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     *
     * @param signupRequest
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO signupRequest){
        logger.info("Starting signup request for user = " + signupRequest.getUsername());

        ResponseMessage responseMessage = new ResponseMessage();
        boolean isFailedRequest = false;
        try {

            if(signupRequest.getUsername().equals("")){
                responseMessage.setMessage(ResponseMessages.NO_USERNAME_PROVIDED);
                isFailedRequest = true;
            }

            if(signupRequest.getEmail().equals("")){
                responseMessage.setMessage(ResponseMessages.NO_EMAIL_PROVIDED);
                isFailedRequest = true;
            }

            if(signupService.checkUsernameAlreadyExists(signupRequest.getUsername())) {
                responseMessage.setMessage(ResponseMessages.USERNAME_EXISTS);
                isFailedRequest = true;
            }

            if(signupService.checkEmailAlreadyExists(signupRequest.getEmail())){
                responseMessage.setMessage(ResponseMessages.EMAIL_EXISTS);
                isFailedRequest = true;

            }

            if(isFailedRequest){
                return new ResponseEntity<>(responseMessage, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            String validationMessage = PasswordValidator.validatePassword(signupRequest.getPassword());
            if(!validationMessage.equals(ValidationMessages.VALID_PASSWORD)){
                return new ResponseEntity<>(validationMessage, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            User newUser = signupService.createNewUser(signupRequest);
            JSONObject signupResponse = signupService.buildSignupResponse(newUser);

            logger.info("successful signup request for user = " + signupRequest.getUsername());

            //TODO :: ADD ASYNCHRONOUS EVENT LISTENER (CHECK, FIX AND TEST)
            this.applicationEventPublisher.publishEvent(new UserRegisteredEvent(this, newUser.getEmail()));
            return new ResponseEntity<>(signupResponse.toJSONString(), HttpStatus.OK);

        }catch(Exception exception){
            exception.printStackTrace();
            logger.error("failed signup request for user = " + signupRequest.getUsername() + " " + exception.getMessage());
            return new ResponseEntity<>(ResponseMessages.UNKNOWN_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
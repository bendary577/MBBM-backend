package com.mbbm.app.controller.users;

import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.http.request.AddNewUserRequestDTO;
import com.mbbm.app.http.request.NewUserRequestDTO;
import com.mbbm.app.http.request.UserInfoUpdateRequestDTO;
import com.mbbm.app.http.response.PageableResponse;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.http.response.messages.ResponseMessageWithList;
import com.mbbm.app.model.base.User;
import com.mbbm.app.record.UserDTO;
import com.mbbm.app.service.SignupService;
import com.mbbm.app.service.UserService;
import com.mbbm.app.util.validation.NewUserValidator;
import com.mbbm.app.util.validation.PasswordValidator;
import com.mbbm.app.util.validation.ValidationResult;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SignupService signupService;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    //TODO:REVISE MULTITENANT SOLUTION
    //@Autowired
    //TenantContext tenantContext;

    /**
     * method to return paginated user information
     *
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @return
     */
    @GetMapping("/") //TODO : HANDLE OPTIONAL PARAMS
    public ResponseEntity<?> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        PageableResponse<UserDTO> userDTOPageableResponse;
        try {
            userDTOPageableResponse = userService.getAllUsers(pageNumber, pageSize, sortBy);
            return new ResponseEntity<>(userDTOPageableResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {
            userDTOPageableResponse = new PageableResponse<>();
            userDTOPageableResponse.setMessage(ResponseMessages.UNKNOWN_ERROR);
            return new ResponseEntity<>(userDTOPageableResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * return user account information
     */
    @GetMapping("/{userId}/info")
    public ResponseEntity<?> getUserInfo(@PathVariable String userId) {
        JSONObject userInfoResponse = userService.buildUserInfoResponse(userId);
        return new ResponseEntity<>(userInfoResponse.toJSONString(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NewUserRequestDTO newUserRequestDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        boolean isFailedRequest = false;
        try {

            //validate basic user information
            NewUserValidator newUserValidator = new NewUserValidator(this.signupService);
            ValidationResult validationResult = newUserValidator.validate(newUserRequestDTO);
            responseMessage.setMessage(validationResult.getMessage());
            isFailedRequest = validationResult.isFailedRequest();

            if (isFailedRequest) {
                return new ResponseEntity<>(responseMessage, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //validate user password
            validationResult = PasswordValidator.validatePassword(newUserRequestDTO.getPassword());
            if (!validationResult.isFailedRequest()) {
                return new ResponseEntity<>(validationResult.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //create user object and save in db
            User newUser = signupService.createNewUser(newUserRequestDTO);
            JSONObject signupResponse = signupService.buildSignupResponse(newUser);

            logger.info("successful signup request for user = " + newUserRequestDTO.getUsername());
            return new ResponseEntity<>(signupResponse.toJSONString(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("failed signup request for user = " + newUserRequestDTO.getUsername() + " " + exception.getMessage());
            return new ResponseEntity<>(ResponseMessages.UNKNOWN_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}

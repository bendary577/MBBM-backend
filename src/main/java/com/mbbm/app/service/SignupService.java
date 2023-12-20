package com.mbbm.app.service;

import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.model.base.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SignupService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ProfileService profileService;

    public User createNewUser(SignupRequestDTO signupRequestDTO){
        Set<Role> roles = roleService.generateRolesListForNewUser(signupRequestDTO.getUserType());
        User user = userService.buildNewUserObject(signupRequestDTO, roles);
        addressService.buildNewAddressObject(signupRequestDTO, user);
        contactService.buildNewContactObject(signupRequestDTO, user);
        profileService.buildNewProfileObject(signupRequestDTO, user);
        return user;
    }

    public JSONObject buildSignupResponse(User user){
        //TODO : decide which info should return after registration
        Map<String , Object> signupInfo = new HashMap<>();
        signupInfo.put("username", user.getUsername());

        JSONObject signupResponse = new JSONObject();
        signupResponse.put("message", ResponseMessages.SUCCESSFUL_SIGNUP);
        signupResponse.put("data", signupInfo);
        return signupResponse;
    }

    public boolean checkUsernameAlreadyExists(String username){
        return userService.existsByUsername(username);
    }

    public boolean checkEmailAlreadyExists(String email){
        return userService.existsByEmail(email);
    }

}

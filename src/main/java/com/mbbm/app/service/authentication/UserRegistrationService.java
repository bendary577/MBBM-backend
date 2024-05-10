package com.mbbm.app.service.authentication;

import com.mbbm.app.http.request.authentication.UserRegistrationRequestDTO;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.model.base.User;
import com.mbbm.app.service.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserRegistrationService {

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

    @Transactional
    public User createNewUser(UserRegistrationRequestDTO newUserRequestDTO){
        Set<Role> roles = roleService.generateRolesListForNewUser(newUserRequestDTO.getUserType());
        User user = userService.buildNewUserObject(newUserRequestDTO, roles);
        addressService.buildNewAddressObject(newUserRequestDTO, user);
        contactService.buildNewContactObject(newUserRequestDTO, user);
        profileService.buildNewProfileObject(newUserRequestDTO, user);
        return user;
    }

    public UserRegistrationRequestDTO buildAdminUserInfoObject(){
        //TODO : configure this info ... this must not be hardcoded like this
        UserRegistrationRequestDTO userRegistrationRequestDTO = new UserRegistrationRequestDTO();
        userRegistrationRequestDTO.setFirstName("admin");
        userRegistrationRequestDTO.setLastName("admin");
        userRegistrationRequestDTO.setUsername("admin");
        userRegistrationRequestDTO.setEmail("admin@admin");
        userRegistrationRequestDTO.setPassword("admin");
        userRegistrationRequestDTO.setBirthDate("27-01-1998");
        userRegistrationRequestDTO.setNationality("Egypt");
        userRegistrationRequestDTO.setCompany(false);
        userRegistrationRequestDTO.setUserType(1);
        userRegistrationRequestDTO.setGender(1);
        userRegistrationRequestDTO.setCountry("Egypt");
        userRegistrationRequestDTO.setState("Cairo");
        userRegistrationRequestDTO.setCity("Helwan");
        userRegistrationRequestDTO.setContactType(1);
        userRegistrationRequestDTO.setContactValue("1099482906");
        userRegistrationRequestDTO.setCountryCode("+20");
        return userRegistrationRequestDTO;
    }

    public void registerSuperAdminUser(){
        UserRegistrationRequestDTO userRegistrationRequestDTO = buildAdminUserInfoObject();
        User user = userService.getUserByUserName(userRegistrationRequestDTO.getUsername());
        if(user == null){
            createNewUser(userRegistrationRequestDTO);
        }
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

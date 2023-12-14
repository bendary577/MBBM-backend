package com.mbbm.app.service;

import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.enums.ERole;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.model.base.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SignupService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public Set<Role> generateRolesListForNewUser(String userType){
        Set<Role> roles = new HashSet<>();
        ERole role = ERole.getRoleByName(userType);
        switch(role){
            case ROLE_MODERATOR:
                Role moderatorRole = roleService.findByName(ERole.ROLE_MODERATOR);
                roles.add(moderatorRole);
                break;
            case ROLE_ADMIN:
                Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
                roles.add(adminRole);
                break;
            case ROLE_MEDIA_BUYER:
                Role mediaBuyerRole = roleService.findByName(ERole.ROLE_MEDIA_BUYER);
                roles.add(mediaBuyerRole);
                break;
            default:
                Role userRole = roleService.findByName(ERole.ROLE_USER);
                roles.add(userRole);
        }
        return roles;
    }

    public User createNewUser(SignupRequestDTO signupRequest){
        Set<Role> roles = this.generateRolesListForNewUser(signupRequest.getUserType());
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setRoles(roles);
        //TODO : set user domain here
        userService.save(user);
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

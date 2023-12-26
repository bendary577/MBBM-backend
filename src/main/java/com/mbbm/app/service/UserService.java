package com.mbbm.app.service;

import com.mbbm.app.enums.EGender;
import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.http.response.PageableResponse;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional //TODO : Search about this annotation
    public ResponseMessage getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedUsers = userRepository.findAll(pageable);
        ResponseMessage responseMessages = new PageableResponse(pagedUsers.getTotalPages(),
                pagedUsers.getTotalElements(),
                pagedUsers.getNumberOfElements(),
                pagedUsers.getSize(),
                pagedUsers.isLast(),
                pagedUsers.isFirst(),
                "users returned successfully",
                pagedUsers.getContent().toString());
        return responseMessages;
    }

    public User getAllUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getAllUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(String userId) {

        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        return user.orElse(null);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User buildNewUserObject(@org.jetbrains.annotations.NotNull SignupRequestDTO signupRequestDTO, Set<Role> roles){
        User user = new User();
        user.setFirstName(signupRequestDTO.getFirstName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setUsername(signupRequestDTO.getUsername());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setBirthdate(signupRequestDTO.getBirthDate());
        user.setNationality(signupRequestDTO.getNationality());
        user.setCompany(signupRequestDTO.isCompany());
        user.setGender(EGender.valueOf(signupRequestDTO.getGender()));
        user.setRoles(roles);
        user.setTimestamp(new Date().toString());
        save(user);
        return user;
    }
    public void save(User user){
        userRepository.save(user);
    }

    public JSONObject buildUserInfoResponse(String userId) {
        User user = getUserById(userId);
        JSONObject userInfoResponse = new JSONObject();
        Map<String , Object> userInfo = new HashMap<>();
        if(user == null){
            userInfoResponse.put("message", ResponseMessages.USER_NOT_AVAILABLE);
        }else{
            userInfo.put("firstName", user.getFirstName());
            userInfo.put("lastName", user.getLastName());
            userInfo.put("username", user.getUsername());
            userInfo.put("email", user.getEmail());
            userInfoResponse.put("message", ResponseMessages.USER_INFO_RETURNED_SUCCESSFULLY);

        }
        userInfoResponse.put("data", userInfo);
        return userInfoResponse;
    }


}

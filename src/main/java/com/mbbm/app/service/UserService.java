package com.mbbm.app.service;

import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

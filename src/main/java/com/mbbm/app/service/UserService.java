package com.mbbm.app.service;

import com.mbbm.app.enums.EGender;
import com.mbbm.app.http.request.authentication.UserRegistrationRequestDTO;
import com.mbbm.app.http.response.PageableResponse;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.mapper.UserMapper;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.model.base.User;
import com.mbbm.app.record.UserDTO;
import com.mbbm.app.repository.UserRepository;
import org.json.simple.JSONObject;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class UserService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;


    public UserService(){
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    // TODO : Search about this annotation
    // @Transactional
    public PageableResponse<UserDTO> getAllUsers(int pageNumber, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy).descending());
        Page<User> pagedUsers = userRepository.findAll(pageRequest);
        List<UserDTO> users = userMapper.entityToDTO(pagedUsers.getContent());
        return new PageableResponse<UserDTO>(pagedUsers.getTotalPages(),
                pagedUsers.getTotalElements(),
                pagedUsers.getNumberOfElements(),
                pagedUsers.getSize(),
                pagedUsers.isLast(),
                pagedUsers.isFirst(),
                "users returned successfully",
                users
                );
    }

    public Optional<User> getAllUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getAllUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(String userId) {

        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        return user.orElse(null);
    }

    public User getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        return user.orElse(null);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User buildNewUserObject(@NotNull UserRegistrationRequestDTO newUserRequestDTO, Set<Role> roles){
        User user = new User();
        user.setFirstName(newUserRequestDTO.getFirstName());
        user.setLastName(newUserRequestDTO.getLastName());
        user.setUsername(newUserRequestDTO.getUsername());
        user.setEmail(newUserRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(newUserRequestDTO.getPassword()));
        user.setBirthdate(newUserRequestDTO.getBirthDate());
        user.setNationality(newUserRequestDTO.getNationality());
        user.setCompany(newUserRequestDTO.isCompany());
        user.setGender(EGender.valueOf(newUserRequestDTO.getGender()));
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

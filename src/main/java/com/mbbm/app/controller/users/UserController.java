package com.mbbm.app.controller.users;

import com.mbbm.app.http.request.AddNewUserRequestDTO;
import com.mbbm.app.http.request.UserInfoUpdateRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.User;
import com.mbbm.app.multitenant.TenantContext;
import com.mbbm.app.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    TenantContext tenantContext;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestParam int pageNumber, @RequestParam int pageSize){
        ResponseMessage responseMessage = userService.getAllUsers(pageNumber, pageSize);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * return user account information
     * */
    @GetMapping("/{userId}/info")
    public ResponseEntity<?> getUserInfo(@PathVariable String userId) {
        JSONObject userInfoResponse = userService.buildUserInfoResponse(userId);
        return new ResponseEntity<>(userInfoResponse.toJSONString(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserInfoUpdateRequestDTO userInfoUpdateRequestDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AddNewUserRequestDTO addNewUserRequestDTO) {
        User user = new User();
        user.setTenantId(tenantContext.getCurrentTenant());
        user.setUsername("test1");
        user.setFirstName("test1");
        user.setLastName("test1");
        user.setEmail("test@test");
        user.setPassword("test1");
        user.setDeleted(false);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

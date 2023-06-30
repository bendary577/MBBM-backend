package com.mbbm.app.controller;

import com.mbbm.app.model.base.User;
import com.mbbm.app.multitenant.TenantContext;
import com.mbbm.app.service.UserService;
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
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> cities = userService.getAllUsers();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{userId}/info")
    public ResponseEntity<User> getUserInfo(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    //@RequestBody User user
    public ResponseEntity<?> save() {
        User user = new User();
        user.setTenantId(tenantContext.getCurrentTenant());
        user.setUsername("test1");
        user.setName("test1");
        user.setEmail("test@test");
        user.setPassword("test1");
        user.setPhone("0104342346");
        user.setAddress("test address");
        user.setActivated(true);
        user.setBlocked(false);
        user.setDeleted(false);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

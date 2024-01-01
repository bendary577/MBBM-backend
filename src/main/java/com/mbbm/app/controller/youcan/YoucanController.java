package com.mbbm.app.controller.youcan;

import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.youcan.YoucanAuthService;
import com.mbbm.app.youcan.YoucanLoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/youcan")
public class YoucanController {

    @Autowired
    YoucanAuthService youcanAuthService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody YoucanLoginRequestDTO youcanLoginRequestDTO) {
        String response = youcanAuthService.login(youcanLoginRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

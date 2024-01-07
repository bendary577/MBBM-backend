package com.mbbm.app.controller.youcan;

import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.youcan.service.YoucanIntegrationService;
import com.mbbm.app.youcan.dto.integration.YoucanLoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/youcanIntegration")
public class YoucanIntegrationController {

    @Autowired
    YoucanIntegrationService youcanAuthService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/{profileId}/integrate")
    public ResponseEntity<ResponseMessage> login(@PathVariable String profileId, @RequestBody YoucanLoginRequestDTO youcanLoginRequestDTO) {
        ResponseMessage response = youcanAuthService.login(youcanLoginRequestDTO, profileId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

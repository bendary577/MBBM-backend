package com.mbbm.app.controller.youcan;

import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.service.youcan.YoucanIntegrationService;
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

    Logger logger = LoggerFactory.getLogger(YoucanIntegrationController.class);

    @PostMapping("/integrate")
    public ResponseEntity<ResponseMessage> integrateYoucan(@RequestBody YoucanLoginRequestDTO youcanLoginRequestDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            YoucanIntegration youcanIntegration = youcanAuthService.integrateYoucan(youcanLoginRequestDTO);
            responseMessage.setData(youcanIntegration);
            responseMessage.setMessage("your youcan store was successfully integrated");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception exception) {
            responseMessage.setMessage(exception.getMessage());
            responseMessage.setData(null);
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

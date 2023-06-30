package com.mbbm.app.controller.whatsapp;

import com.mbbm.app.multitenant.TenantContext;
import com.mbbm.app.whatsapp.services.WhatsappMessageTemplateService;
import com.mbbm.app.whatsapp.services.WhatsappSendMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/whatsapp/messages/")
public class WhatsappSendMessageController {


    private WhatsappSendMessageService whatsappSendMessageService;

    public WhatsappSendMessageController(WhatsappSendMessageService whatsappSendMessageService){
        this.whatsappSendMessageService = whatsappSendMessageService;
    }

    @GetMapping("/send-template-message")
    public ResponseEntity<String> sendTemplateMessage(){
        String responseMessage = this.whatsappSendMessageService.sendWhatsappMessageTemplate();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/send-text-message")
    public ResponseEntity<String> sendTextMessage(){
        String responseMessage = this.whatsappSendMessageService.sendWhatsappTextMessage();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/send-image-message")
    public ResponseEntity<String> sendImageMessage(){
        String responseMessage = this.whatsappSendMessageService.sendWhatsappMessageWithImage();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}

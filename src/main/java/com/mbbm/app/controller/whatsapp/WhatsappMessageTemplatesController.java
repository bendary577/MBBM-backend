package com.mbbm.app.controller.whatsapp;

import com.mbbm.app.whatsapp.messageTemplates.MessageTemplatesResponse;
import com.mbbm.app.whatsapp.services.WhatsappMessageTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/whatsapp/message-templates")
public class WhatsappMessageTemplatesController {

    private WhatsappMessageTemplateService whatsappMessageTemplateService;


    public WhatsappMessageTemplatesController(WhatsappMessageTemplateService whatsappMessageTemplateService){
        this.whatsappMessageTemplateService = whatsappMessageTemplateService;
    }


    @GetMapping("/create-message-template")
    public ResponseEntity<String> createMessageTemplate(){
        String responseMessage = this.whatsappMessageTemplateService.createMessageTemplate();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/get-message-templates")
    public ResponseEntity<List<MessageTemplatesResponse>> getMessageTemplates(){
        List<MessageTemplatesResponse> messageTemplatesResponseList = this.whatsappMessageTemplateService.getMessageTemplates();
        return new ResponseEntity<>(messageTemplatesResponseList, HttpStatus.OK);
    }

}

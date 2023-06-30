package com.mbbm.app.controller.whatsapp;

import com.mbbm.app.multitenant.TenantContext;
import com.mbbm.app.whatsapp.phones.PhoneResponseObject;
import com.mbbm.app.whatsapp.services.WhatsappMediaService;
import com.mbbm.app.whatsapp.services.WhatsappMessageTemplateService;
import com.mbbm.app.whatsapp.services.WhatsappPhonesService;
import com.mbbm.app.whatsapp.services.WhatsappSendMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/whatsapp/media")
public class WhatsappMediaController {

    private WhatsappMediaService whatsappMediaService;
    private WhatsappPhonesService whatsappPhonesService;


    public WhatsappMediaController(
            WhatsappMediaService whatsappMediaService,
            WhatsappPhonesService whatsappPhonesService){
        this.whatsappMediaService = whatsappMediaService;
        this.whatsappPhonesService = whatsappPhonesService;
    }

    @GetMapping("/upload")
    public ResponseEntity<String> uploadMediaToWhatsappAPI(){
        List<PhoneResponseObject> phoneResponseObjects = this.whatsappPhonesService.getWhatsappBusinessAccountPhones();
        String responseMessage = this.whatsappMediaService.uploadMediaToWhatsappAPI(phoneResponseObjects.get(0).getDisplay_phone_number());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}

package com.mbbm.app.controller.whatsapp;

import com.mbbm.app.whatsapp.phones.PhoneResponseObject;
import com.mbbm.app.whatsapp.services.WhatsappMediaService;
import com.mbbm.app.whatsapp.services.WhatsappPhonesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/whatsapp/phones")
public class WhatsappPhonesController {

    private WhatsappPhonesService whatsappPhonesService;


    public WhatsappPhonesController(WhatsappPhonesService whatsappPhonesService){
        this.whatsappPhonesService = whatsappPhonesService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PhoneResponseObject>> getWhatsappBusinessAccountPhones(){
        List<PhoneResponseObject> phoneResponseObjectList = this.whatsappPhonesService.getWhatsappBusinessAccountPhones();
        return new ResponseEntity<>(phoneResponseObjectList, HttpStatus.OK);
    }

}

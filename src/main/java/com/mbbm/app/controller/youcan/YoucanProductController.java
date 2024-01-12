package com.mbbm.app.controller.youcan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.youcan.dto.YoucanProductUpdateRequestDTO;
import com.mbbm.app.youcan.service.YoucanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/youcan")
public class YoucanProductController {

    @Autowired
    private YoucanProductService youcanService;

    @GetMapping("/{profileId}/getProducts")
    public ResponseEntity<ResponseMessage> getProducts(@PathVariable String profileId) {
        ResponseMessage response = youcanService.getProducts(profileId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{profileId}/updateProduct/{productId}", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> updateProduct(@PathVariable String profileId,
                                                         @PathVariable String productId,
                                                         @RequestBody String youcanProductUpdateRequestJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map
                = objectMapper.readValue(youcanProductUpdateRequestJson, HashMap.class);
        YoucanProductUpdateRequestDTO youcanProductUpdateRequestDTO = new YoucanProductUpdateRequestDTO();
        youcanProductUpdateRequestDTO.setUpdatedData(map);
        ResponseMessage response = youcanService.updateProduct(profileId, productId, youcanProductUpdateRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

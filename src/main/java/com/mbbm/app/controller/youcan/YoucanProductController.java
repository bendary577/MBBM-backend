package com.mbbm.app.controller.youcan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbm.app.exception.youcan.YoucanGetProductsException;
import com.mbbm.app.http.request.youcan.YoucanMassProductUpdateRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.youcan.dto.YoucanProductUpdateRequestDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductsResponseDTO;
import com.mbbm.app.service.youcan.YoucanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/youcan")
public class YoucanProductController {

    @Autowired
    private YoucanProductService youcanService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<ResponseMessage> getAllProducts() {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            List<YoucanProductDTO> youcanProductDTOList = youcanService.getAllProducts();
            if(youcanProductDTOList.isEmpty()){
                throw new YoucanGetProductsException("we encountered an error while trying to get youcan store products");
            }
            responseMessage.setMessage("youcan store products returned successfully");
            responseMessage.setData(youcanProductDTOList);
        }catch (Exception exception){
            responseMessage.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{pageCount}/getProductsPaginated")
    public ResponseEntity<ResponseMessage> getProductsPaginated(@PathVariable String pageCount) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            YoucanProductsResponseDTO youcanProductsResponseDTO = youcanService.getProductsPerPage(Integer.valueOf(pageCount));
            if(youcanProductsResponseDTO == null){
                throw new YoucanGetProductsException("we encountered an error while trying to get youcan store products");
            }
            responseMessage.setMessage("youcan store products returned successfully");
            responseMessage.setData(youcanProductsResponseDTO);
        }catch (Exception exception){
            responseMessage.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateProduct/{productId}", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> updateProduct(@PathVariable String productId,
                                                         @RequestBody String youcanProductUpdateRequestJson) throws JsonProcessingException {
        ResponseMessage response = new ResponseMessage();
        try{
           ObjectMapper objectMapper = new ObjectMapper();
           HashMap<String, Object> map
                   = objectMapper.readValue(youcanProductUpdateRequestJson, HashMap.class);
           YoucanProductUpdateRequestDTO youcanProductUpdateRequestDTO = new YoucanProductUpdateRequestDTO();
           youcanProductUpdateRequestDTO.setUpdatedData(map);
           YoucanProductDTO youcanProductDTO = youcanService.updateProduct(productId, youcanProductUpdateRequestDTO);
           response.setData(youcanProductDTO);
           response.setMessage("youcan product was updated successfully");
        }catch(Exception exception){
            response.setMessage(exception.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateAllProducts", method = RequestMethod.GET, consumes="application/json")
    public ResponseEntity<ResponseMessage> updateAllProducts(@RequestBody YoucanMassProductUpdateRequestDTO youcanMassProductUpdateRequestDTO) {
        ResponseMessage response = new ResponseMessage();
        try{
            youcanService.updateAllProducts(youcanMassProductUpdateRequestDTO);
            response.setMessage("youcan product was updated successfully");
        }catch(Exception exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/downloadProductsUpdateFile")
    public ResponseEntity<ResponseMessage> downloadProductsUpdateFile() {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            List<YoucanProductDTO> youcanProductDTOList = youcanService.getAllProducts();
            if(youcanProductDTOList.isEmpty()){
                throw new YoucanGetProductsException("we encountered an error while trying to get youcan store products");
            }
            responseMessage.setMessage("youcan store products returned successfully");
            responseMessage.setData(youcanProductDTOList);
        }catch (Exception exception){
            responseMessage.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}

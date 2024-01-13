package com.mbbm.app.youcan.service;

import com.google.gson.Gson;
import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.repository.YoucanIntegrationRepository;
import com.mbbm.app.repository.YoucanStoreRepository;
import com.mbbm.app.service.ProfileService;
import com.mbbm.app.util.compression.CompressionUtility;
import com.mbbm.app.youcan.YoucanSettings;
import com.mbbm.app.youcan.dto.YoucanProductUpdateRequestDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductsResponseDTO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

/***
 * @author mohamed.bendary
 * service responsible for product processing in youcan product api
 */
@Service
public class YoucanProductService {

    @Autowired
    private YoucanSettings youcanConfigurations;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private YoucanIntegrationRepository youcanIntegrationRepository;

    @Autowired
    private YoucanStoreRepository youcanStoreRepository;

    @Autowired
    private CompressionUtility compressionUtility;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public YoucanProductService(){}

    //TODO:ADD PAGINATION INFORMATION
    public ResponseMessage getProducts(String profileId){

        ResponseMessage responseMessage = null;
        OkHttpClient client = new OkHttpClient();

        Profile profile = profileService.getProfileById(profileId);
        YoucanIntegration youcanIntegration = profile.getYoucanIntegration();
        if(youcanIntegration == null){

        }
        String youcanToken = youcanIntegration.getToken();
        Request request = new Request.Builder()
                .url(youcanConfigurations.YOUCAN_BASE_API_URL + "/products?include=variants")
                .get()
                .addHeader("Authorization", "Bearer "+youcanToken)
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseMessage = new ResponseMessage();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String decodedResponseBody = compressionUtility.decode(response.body().bytes());
                YoucanProductsResponseDTO youcanProductsResponseDTO = gson.fromJson(decodedResponseBody, YoucanProductsResponseDTO.class);
                responseMessage.setData(youcanProductsResponseDTO);
                responseMessage.setMessage("youcan store products returned successfully");
            } else {
                responseMessage.setData("");
                responseMessage.setMessage("failed to get youcan store products");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    public ResponseMessage updateProduct(String profileId, String productId, @NotNull YoucanProductUpdateRequestDTO youcanProductUpdateRequestDTO) {
        //--------REFACTORING----------------------
        Response response;
        ResponseMessage responseMessage = null;
        OkHttpClient client = new OkHttpClient();

        Profile profile = profileService.getProfileById(profileId);
        YoucanIntegration youcanIntegration = profile.getYoucanIntegration();
        if(youcanIntegration == null){}
        String youcanToken = youcanIntegration.getToken();
        //------------------------------------------
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : youcanProductUpdateRequestDTO.getUpdatedData().entrySet()) {
            String updatedAttributeKey = entry.getKey();
            Object updatedAttributeValue = entry.getValue();
            builder.addFormDataPart(updatedAttributeKey, updatedAttributeValue.toString());
        }
        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder()
                .url(youcanConfigurations.YOUCAN_BASE_API_URL + "/products/update/"+productId)
                .post(multipartBody)
                .addHeader("Authorization", "Bearer "+youcanToken)
                .addHeader("Accept", "application/json")
                .build();

        try {
            response = client.newCall(request).execute();
            responseMessage = new ResponseMessage();
            String decodedResponseBody;
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                decodedResponseBody = compressionUtility.decode(response.body().bytes());
                YoucanProductDTO youcanProductDTO = gson.fromJson(decodedResponseBody, YoucanProductDTO.class);
                responseMessage.setData(youcanProductDTO);
                responseMessage.setMessage("youcan store products returned successfully");
            } else {
                decodedResponseBody = compressionUtility.decode(response.body().bytes());
                responseMessage.setData("");
                responseMessage.setMessage("failed to get youcan store products");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

}

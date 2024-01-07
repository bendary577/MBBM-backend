package com.mbbm.app.youcan.service;

import com.google.gson.Gson;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.youcan.YouCanStore;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.service.ProfileService;
import com.mbbm.app.util.compression.CompressionUtility;
import com.mbbm.app.youcan.YoucanSettings;
import com.mbbm.app.youcan.dto.integration.YoucanIntegrationLoginResponseDTO;
import com.mbbm.app.youcan.dto.integration.YoucanLoginRequestDTO;
import com.mbbm.app.youcan.dto.integration.YoucanStoreDTO;
import com.mbbm.app.repository.YoucanIntegrationRepository;
import com.mbbm.app.repository.YoucanStoreRepository;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class YoucanIntegrationService {

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

    public YoucanIntegrationService(){}

    public ResponseMessage login(@NotNull YoucanLoginRequestDTO youcanLoginRequestDTO, String profileId){
        Response response = null;
        ResponseMessage responseMessage = null;
        OkHttpClient client = new OkHttpClient();

        // Create the request body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", youcanLoginRequestDTO.getUsername())
                .addFormDataPart("password", youcanLoginRequestDTO.getPassword())
                .build();

        Request request = new Request.Builder()
                .url(youcanConfigurations.YOUCAN_BASE_API_URL + "/auth/login")
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .build();

        try {
            response = client.newCall(request).execute();
            responseMessage = new ResponseMessage();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String decoredResponseBody = compressionUtility.decode(response.body().bytes());
                YoucanIntegrationLoginResponseDTO youcanIntegrationLoginResponseDTO = gson.fromJson(decoredResponseBody, YoucanIntegrationLoginResponseDTO.class);
                String responseMessageValue = saveYoucanIntegration(youcanIntegrationLoginResponseDTO, profileId);
                responseMessage.setData(youcanIntegrationLoginResponseDTO);
                responseMessage.setMessage(responseMessageValue);
            } else {
                responseMessage.setData("");
                responseMessage.setMessage("youcan integration finished failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    @Transactional
    public String saveYoucanIntegration(YoucanIntegrationLoginResponseDTO youcanIntegrationLoginResponseDTO, String profileId){
        String responseMessage = "";
        Profile profile = profileService.getProfileById(profileId);
        YoucanIntegration savedYoucanIntegration = profile.getYoucanIntegration();
        if(savedYoucanIntegration == null){
            responseMessage = "youcan integration finished successfully";
            YoucanIntegration youcanIntegration = new YoucanIntegration();
            youcanIntegration.setToken(youcanIntegrationLoginResponseDTO.getToken());
            youcanIntegration.setExpiredAt(youcanIntegrationLoginResponseDTO.getExpired_at());
            youcanIntegration.setProfile(profile);
            youcanIntegrationRepository.save(youcanIntegration);
            List<YouCanStore> youCanStoreList = new LinkedList<>();
            for(YoucanStoreDTO youcanStoreDTO : youcanIntegrationLoginResponseDTO.getStores()){
                YouCanStore youCanStore = new YouCanStore();
                youCanStore.setStoreId(youcanStoreDTO.getStore_id());
                youCanStore.setSlug(youcanStoreDTO.getSlug());
                youCanStore.setActive(youcanStoreDTO.isIs_active());
                youCanStore.setEmailVerified(youcanStoreDTO.isIs_email_verified());
                youCanStore.setYoucanIntegration(youcanIntegration);
                youCanStoreList.add(youCanStore);
            }
            youcanStoreRepository.saveAll(youCanStoreList);
        }else{
            responseMessage = "youcan is already integrated";
        }
        return responseMessage;
    }


}
